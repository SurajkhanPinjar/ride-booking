package com.uber.ridebooking.booking.controller;

import com.uber.ridebooking.booking.dto.RideRequest;
import com.uber.ridebooking.booking.entity.Ride;
import com.uber.ridebooking.booking.service.RideService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rides")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "🚕 Ride Controller", description = "Handles ride booking, start, end, and cancel operations.")
public class RideController {

    private final RideService rideService;

    @PostMapping
    @Operation(summary = "Book a ride", description = "Create a new ride request with source, destination, and vehicle type.")
    public ResponseEntity<Ride> bookRide(@RequestBody RideRequest request) {
        log.info("📥 Booking ride request received: {}", request);
        Ride ride = rideService.bookRide(request);
        log.info("✅ Ride booked successfully with ID: {}", ride.getId());
        return ResponseEntity.ok(ride);
    }

    @PutMapping("/{rideId}/start")
    @Operation(summary = "Start a ride", description = "Mark the ride as STARTED by ride ID.")
    public ResponseEntity<String> startRide(@PathVariable Long rideId) {
        log.info("🚗 Starting ride with ID: {}", rideId);
        rideService.startRide(rideId);
        return ResponseEntity.ok("Ride started successfully.");
    }

    @PutMapping("/{rideId}/end")
    @Operation(summary = "End a ride", description = "Mark the ride as ENDED by ride ID.")
    public ResponseEntity<String> endRide(@PathVariable Long rideId) {
        log.info("🛑 Ending ride with ID: {}", rideId);
        rideService.endRide(rideId);
        return ResponseEntity.ok("Ride ended successfully.");
    }

    @PutMapping("/{rideId}/cancel")
    @Operation(summary = "Cancel a ride", description = "Mark the ride as CANCELLED by ride ID.")
    public ResponseEntity<String> cancelRide(@PathVariable Long rideId) {
        log.info("🚫 Cancelling ride with ID: {}", rideId);
        rideService.cancelRide(rideId);
        return ResponseEntity.ok("Ride cancelled successfully.");
    }
}