package com.uber.ridebooking.booking.dto;

import com.uber.ridebooking.vehicle.dto.VehicleType;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {
    private Long riderId;
    private String source;
    private String destination;

    private String pickupLocation;

    private VehicleType preferredVehicleType;
}