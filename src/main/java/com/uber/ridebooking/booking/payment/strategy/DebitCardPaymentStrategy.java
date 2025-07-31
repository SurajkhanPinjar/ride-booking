package com.uber.ridebooking.booking.payment.strategy;

import com.uber.ridebooking.booking.payment.enums.PaymentStatus;
import org.springframework.stereotype.Component;

@Component("DEBITCARD")
public class DebitCardPaymentStrategy implements PaymentStrategy{
    @Override
    public PaymentStatus pay(Double amount) {
        System.out.println("💳 Debit Card charged for ₹" + amount);
        return PaymentStatus.SUCCESS;
    }
}
