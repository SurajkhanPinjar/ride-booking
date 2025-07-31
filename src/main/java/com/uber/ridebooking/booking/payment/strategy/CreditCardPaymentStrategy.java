package com.uber.ridebooking.booking.payment.strategy;

import com.uber.ridebooking.booking.payment.enums.PaymentStatus;
import org.springframework.stereotype.Component;

@Component("CREDITCARD")
public class CreditCardPaymentStrategy implements PaymentStrategy{
    @Override
    public PaymentStatus pay(Double amount) {
        System.out.println("💳 Credit Card charged for ₹" + amount);
        return PaymentStatus.SUCCESS;
    }
}
