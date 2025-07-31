package com.uber.ridebooking.booking.payment.service.impl;


import com.uber.ridebooking.booking.entity.Booking;
import com.uber.ridebooking.booking.payment.dto.PaymentRequest;
import com.uber.ridebooking.booking.payment.dto.PaymentResponse;
import com.uber.ridebooking.booking.payment.entity.Payment;
import com.uber.ridebooking.booking.payment.enums.PaymentStatus;
import com.uber.ridebooking.booking.payment.repository.PaymentRepository;
import com.uber.ridebooking.booking.payment.service.PaymentService;
import com.uber.ridebooking.booking.payment.strategy.PaymentStrategy;
import com.uber.ridebooking.booking.payment.strategy.PaymentStrategyFactory;
import com.uber.ridebooking.booking.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    private final PaymentStrategyFactory paymentStrategyFactory;

    @Override
    public PaymentResponse makePayment(PaymentRequest paymentRequest) {
        Booking booking = bookingRepository.findById(paymentRequest.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        PaymentStrategy strategy = paymentStrategyFactory.resolve(paymentRequest.getMethod());
        PaymentStatus status = strategy.pay(paymentRequest.getAmount());

        Payment payment = Payment.builder()
                .amount(paymentRequest.getAmount())
                .paymentStatus(status)
                .method(paymentRequest.getMethod())
                .paymentTime(LocalDateTime.now())
                .booking(booking)
                .build();

        Payment saved = paymentRepository.save(payment);

        return PaymentResponse.builder()
                .paymentId(saved.getId())
                .bookingId(booking.getId())
                .amount(saved.getAmount())
                .status(saved.getPaymentStatus())
                .build();
    }
}
