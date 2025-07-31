package com.uber.ridebooking.driver.factory;

import com.uber.ridebooking.driver.dto.DriverRequest;
import com.uber.ridebooking.driver.entity.Driver;
import com.uber.ridebooking.driver.entity.Status;
import com.uber.ridebooking.vehicle.entity.Vehicle;

public class DriverFactory {

    public static Driver createDriver(DriverRequest request) {
        return Driver.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(request.getPassword())
                .phone(request.getPhone())
                .licenseNumber(request.getLicenseNumber())
                .status(Status.AVAILABLE) // Default to AVAILABLE
                .location(request.getLocation())
                .vehicle(
                        Vehicle.builder()
                                .licensePlate(request.getVehicleNumber())
                                .model(request.getVehicleModel())
                                .color(request.getVehicleColor())
                                .type(request.getVehicleType())
                                .build()
                )
                .rating(0.0)                // ✅ Avg rating starts at 0.0
                .totalRatingSum(0.0)        // ✅ No ratings yet
                .totalRatingsCount(0)       // ✅ No ratings yet
                .active(true)
                .build();
    }
}