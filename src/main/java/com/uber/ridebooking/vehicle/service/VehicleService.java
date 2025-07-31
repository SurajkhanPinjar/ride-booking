package com.uber.ridebooking.vehicle.service;

import com.uber.ridebooking.vehicle.dto.VehicleRequest;
import com.uber.ridebooking.vehicle.dto.VehicleResponse;

import java.util.List;

public interface VehicleService {
    VehicleResponse addVehicle(VehicleRequest request);
    List<VehicleResponse> getVehiclesByDriver(Long driverId);
}
