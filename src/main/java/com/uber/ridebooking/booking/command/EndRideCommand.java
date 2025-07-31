package com.uber.ridebooking.booking.command;

import com.uber.ridebooking.booking.service.RideService;

public class EndRideCommand implements RideCommand{

    private final RideService rideService;
    private final Long id;

    public EndRideCommand(RideService rideService, Long id) {
        this.rideService = rideService;
        this.id = id;
    }

    @Override
    public void execute() {
        rideService.endRide(id);
    }
}
