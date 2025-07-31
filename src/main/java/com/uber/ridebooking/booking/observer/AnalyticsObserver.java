package com.uber.ridebooking.booking.observer;

import com.uber.ridebooking.booking.entity.Booking;
import com.uber.ridebooking.booking.observer.BookingObserver;
import org.springframework.stereotype.Component;

@Component
public class AnalyticsObserver implements BookingObserver {
    @Override
    public void onBookingConfirmed(Booking booking) {
        System.out.println("📊 Analytics updated for booking location: " + booking.getPickupLocation());
    }

    @Override
    public void onBookingStatusChanges(Booking booking) {
        // Optional logging or nothing
        System.out.println("📈 Analytics: Ride status changed to " + booking.getStatus());
    }

}