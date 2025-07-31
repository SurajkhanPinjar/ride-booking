package com.uber.ridebooking.booking.observer;

import com.uber.ridebooking.booking.entity.Booking;

import java.util.ArrayList;
import java.util.List;

public class BookingStatusSubject {
    private final List<BookingObserver> observers = new ArrayList<>();

    public void  registerObserver(BookingObserver bookingStatusObserver){
        observers.add(bookingStatusObserver);
    }

    public void notifyObservers(Booking booking){
        for(BookingObserver bookingStatusObserver: observers){
            bookingStatusObserver.onBookingConfirmed(booking);
        }
    }
}
