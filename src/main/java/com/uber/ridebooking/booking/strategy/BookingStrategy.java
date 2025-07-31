package com.uber.ridebooking.booking.strategy;

import com.uber.ridebooking.driver.entity.Driver;

public interface BookingStrategy {
    Driver findDriver(String source);
}
