package com.uber.ridebooking.booking.payment.strategy;

import com.uber.ridebooking.booking.payment.enums.PaymentStatus;
import org.springframework.stereotype.Component;

@Component("WALLET")
public class WalletPaymentStrategy implements PaymentStrategy{
    @Override
    public PaymentStatus pay(Double amount) {
        System.out.println(" Wallet charged for ₹" + amount);
        return PaymentStatus.SUCCESS;
    }
}
