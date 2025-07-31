package com.uber.ridebooking.booking.payment.service;

import com.uber.ridebooking.booking.payment.dto.PaymentRequest;
import com.uber.ridebooking.booking.payment.dto.PaymentResponse;

public interface PaymentService {
    PaymentResponse makePayment(PaymentRequest paymentRequest);
}
