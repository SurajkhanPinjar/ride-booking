package com.uber.ridebooking.booking.matching.chain;

import com.uber.ridebooking.booking.dto.BookingRequest;
import com.uber.ridebooking.driver.entity.Driver;

import java.util.List;

public abstract class AbstractMatchHandler implements MatchHandler{
    protected MatchHandler next;

    @Override
    public void setNext(MatchHandler nextHandler) {
        this.next = nextHandler;
    }

    @Override
    public Driver handle(BookingRequest request) {
        throw new UnsupportedOperationException("Use handle(request, drivers) instead.");
    }

    public abstract Driver handle(BookingRequest request, List<Driver> filteredDrivers);
}
