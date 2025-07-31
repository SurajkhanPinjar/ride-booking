package com.uber.ridebooking.booking.observer;

import com.uber.ridebooking.booking.entity.Booking;

public interface BookingObserver {
    void onBookingConfirmed(Booking booking);

    void onBookingStatusChanges(Booking booking);
}
