package com.uber.ridebooking.booking.payment.strategy;

import com.uber.ridebooking.booking.payment.enums.PaymentStatus;
import org.springframework.stereotype.Component;

@Component("UPI")
public class UPIPaymentStrategy implements PaymentStrategy{
    @Override
    public PaymentStatus pay(Double amount) {
        System.out.println("✅ UPI payment successful for ₹" + amount);
        return PaymentStatus.SUCCESS;
    }
}
