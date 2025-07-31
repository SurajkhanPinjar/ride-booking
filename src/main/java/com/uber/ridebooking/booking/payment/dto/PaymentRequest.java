package com.uber.ridebooking.booking.payment.dto;

import com.uber.ridebooking.booking.payment.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {
    private Long bookingId;
    private Double amount;
    private PaymentMethod method;

}
