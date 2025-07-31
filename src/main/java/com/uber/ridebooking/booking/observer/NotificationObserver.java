package com.uber.ridebooking.booking.observer;

import com.uber.ridebooking.booking.entity.Booking;
import org.springframework.stereotype.Component;

@Component
public class NotificationObserver implements BookingObserver{

    @Override
    public void onBookingConfirmed(Booking booking) {
        System.out.println("📩 Notification sent to user: " + booking.getRider().getFullName());
    }

    @Override
    public void onBookingStatusChanges(Booking booking) {
        System.out.println("🔔 Notification: Ride status changed to " + booking.getStatus() +
                " for booking #" + booking.getId());
    }
}
