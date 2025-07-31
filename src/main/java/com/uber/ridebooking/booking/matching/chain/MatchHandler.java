package com.uber.ridebooking.booking.matching.chain;

import com.uber.ridebooking.booking.dto.BookingRequest;
import com.uber.ridebooking.driver.entity.Driver;

import java.util.List;

public interface MatchHandler {
    void setNext(MatchHandler nextHandler);
    Driver handle(BookingRequest request);
    Driver handle(BookingRequest request, List<Driver> drivers); // ✅ ADD THIS
}
