package com.uber.ridebooking.booking.entity;

public enum BookingStatus {
    IN_PROGRESS,
    CONFIRMED,
    CANCELLED,
    COMPLETED,
    STARTED;

    public boolean isCompleted(){
        return this == COMPLETED;
    }
}