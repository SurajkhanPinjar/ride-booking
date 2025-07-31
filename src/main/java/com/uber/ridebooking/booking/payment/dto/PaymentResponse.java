package com.uber.ridebooking.booking.payment.dto;

import com.uber.ridebooking.booking.payment.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {
    private Long paymentId;
    private Long bookingId;
    private Double amount;
    private PaymentStatus status;
}
