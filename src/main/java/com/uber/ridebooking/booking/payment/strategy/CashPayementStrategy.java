package com.uber.ridebooking.booking.payment.strategy;

import com.uber.ridebooking.booking.payment.enums.PaymentStatus;
import org.springframework.stereotype.Component;

@Component("CASH")
public class CashPayementStrategy implements PaymentStrategy{
    @Override
    public PaymentStatus pay(Double amount) {
        System.out.println("💵Cash charged for ₹" + amount);
        return PaymentStatus.SUCCESS;
    }
}
