package com.uber.ridebooking.vehicle.service;

import com.uber.ridebooking.driver.entity.Driver;
import com.uber.ridebooking.driver.repository.DriverRepository;
import com.uber.ridebooking.vehicle.dto.VehicleRequest;
import com.uber.ridebooking.vehicle.dto.VehicleResponse;
import com.uber.ridebooking.vehicle.entity.Vehicle;
import com.uber.ridebooking.vehicle.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService{

    private final VehicleRepository vehicleRepository;

    private final DriverRepository driverRepository;

    @Override
    public VehicleResponse addVehicle(VehicleRequest request) {
        Driver driver = driverRepository.findById(request.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver not found"));
        Vehicle vehicle = Vehicle.builder()
                .licensePlate(request.getLicensePlate())
                .type(request.getType())
                .model(request.getModel())
                .color(request.getColor())
                .capacity(request.getCapacity())
                .driver(driver)
                .build();

        Vehicle saved = vehicleRepository.save(vehicle);
        return mapToResponse(saved);
    }

    @Override
    public List<VehicleResponse> getVehiclesByDriver(Long driverId) {
        return vehicleRepository.findByDriverId(driverId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private VehicleResponse mapToResponse(Vehicle v) {
        return VehicleResponse.builder()
                .id(v.getId())
                .licensePlate(v.getLicensePlate())
                .type(v.getType())
                .model(v.getModel())
                .color(v.getColor())
                .capacity(v.getCapacity())
                .driverId(v.getDriver().getId())
                .build();
    }
}
