package com.uber.ridebooking.booking.command;

import com.uber.ridebooking.booking.service.RideService;

public class StartRideCommand implements RideCommand{

    private final RideService rideService;
    private final Long id;

    public StartRideCommand(RideService rideService, Long id) {
        this.rideService = rideService;
        this.id = id;
    }

    @Override
    public void execute() {
        rideService.startRide(id);
    }
}
