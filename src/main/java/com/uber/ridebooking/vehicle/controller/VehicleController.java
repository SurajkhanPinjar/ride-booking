package com.uber.ridebooking.vehicle.controller;

import com.uber.ridebooking.vehicle.dto.VehicleRequest;
import com.uber.ridebooking.vehicle.dto.VehicleResponse;
import com.uber.ridebooking.vehicle.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/vehicles")
@Tag(name = "Vehicle Module", description = "Endpoints for Driver registration and profile")
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping
    @Operation(summary = "Register a New Vehicle")
    public ResponseEntity<VehicleResponse> addVehicle(@RequestBody VehicleRequest request) {
        return ResponseEntity.ok(vehicleService.addVehicle(request));
    }

    @GetMapping("{vehicleId}")
    @Operation(summary = "Get Vehicle  by ID")
    public ResponseEntity<List<VehicleResponse>> getDriverVehicles(@PathVariable Long driverId) {
        return ResponseEntity.ok(vehicleService.getVehiclesByDriver(driverId));
    }
}
