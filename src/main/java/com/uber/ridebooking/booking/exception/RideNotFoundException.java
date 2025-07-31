package com.uber.ridebooking.booking.exception;

public class RideNotFoundException extends Exception{
    public RideNotFoundException(Long id) {
        super("Ride not found with ID: " + id);
    }
}
