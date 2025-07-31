package com.uber.ridebooking.vehicle.dto;

import com.uber.ridebooking.vehicle.dto.VehicleType;
import lombok.Data;

@Data
public class VehicleRequest {
    private String licensePlate;
    private VehicleType type;
    private String model;
    private String color;
    private int capacity;
    private Long driverId;
}