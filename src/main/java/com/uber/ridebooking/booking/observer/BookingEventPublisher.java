package com.uber.ridebooking.booking.observer;

import com.uber.ridebooking.booking.entity.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookingEventPublisher {

    private final List<BookingObserver> observers;

    public void notifyObservers(Booking booking){
        observers.forEach(observers -> observers.onBookingConfirmed(booking));
    }

    public void notifyObserversOnStatusChange(Booking booking){
        observers.forEach(observers -> observers.onBookingStatusChanges(booking));
    }

}
