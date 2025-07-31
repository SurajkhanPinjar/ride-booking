package com.uber.ridebooking.booking.command;

import com.uber.ridebooking.booking.dto.BookingRequest;
import com.uber.ridebooking.booking.entity.Booking;
import com.uber.ridebooking.driver.entity.Driver;

public interface BookingCommand {
    Booking execute(BookingRequest request, Driver driver);
}
