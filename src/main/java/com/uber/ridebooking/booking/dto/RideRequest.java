package com.uber.ridebooking.booking.dto;

import com.uber.ridebooking.vehicle.dto.VehicleType;
import lombok.Data;

@Data
public class RideRequest {
    private Long userId;
    private String pickUpLocation;
    private String  dropLocation;
    private VehicleType vehicleType;
    private Long driverId;
}
