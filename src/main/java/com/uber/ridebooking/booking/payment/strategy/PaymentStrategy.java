package com.uber.ridebooking.booking.payment.strategy;

import com.uber.ridebooking.booking.payment.enums.PaymentStatus;

public interface PaymentStrategy {
    PaymentStatus pay(Double amount);
}
