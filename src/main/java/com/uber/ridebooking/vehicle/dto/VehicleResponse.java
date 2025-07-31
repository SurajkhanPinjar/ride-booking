package com.uber.ridebooking.vehicle.dto;

import com.uber.ridebooking.vehicle.dto.VehicleType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VehicleResponse {
    private Long id;
    private String licensePlate;
    private VehicleType type;
    private String model;
    private String color;
    private int capacity;
    private Long driverId;
}