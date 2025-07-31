package com.uber.ridebooking.booking.command;

import com.uber.ridebooking.booking.dto.RideRequest;
import com.uber.ridebooking.booking.service.RideService;
import com.uber.ridebooking.rider.service.RiderService;
import lombok.RequiredArgsConstructor;


public class BookRideCommand implements RideCommand{
    private final RideService rideService;
    private final RideRequest rideRequest;

    public BookRideCommand(RideService rideService, RideRequest rideRequest) {
        this.rideService = rideService;
        this.rideRequest = rideRequest;
    }

    @Override
    public void execute() {
        rideService.bookRide(rideRequest);
    }
}
