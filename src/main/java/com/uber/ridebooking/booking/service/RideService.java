package com.uber.ridebooking.booking.service;

import com.uber.ridebooking.booking.dto.RideRequest;
import com.uber.ridebooking.booking.entity.Ride;
import com.uber.ridebooking.booking.enums.RideStatus;
import com.uber.ridebooking.booking.repository.RideRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class RideService {

    private final RideRepository rideRepository;

    public Ride bookRide(RideRequest rideRequest){
        Ride ride = Ride.builder()
                .userId(rideRequest.getUserId())
                .driverId(rideRequest.getDriverId())
                .pickupLocation(rideRequest.getPickUpLocation())
                .dropLocation(rideRequest.getDropLocation())
                .vehicleType(rideRequest.getVehicleType())
                .rideStatus(RideStatus.REQUESTED)
                .startTime(LocalDateTime.now())
                .build();
        Ride savedRide = rideRepository.save(ride);
        log.info("✅ Ride booked successfully: Ride ID = {}", savedRide.getId());
        return savedRide;
    }

    public void startRide(Long rideId) {
        Ride ride = rideRepository.findById(rideId).orElseThrow(() -> new RuntimeException("No Ride Found"));
        ride.setRideStatus(RideStatus.STARTED);
        ride.setStartTime(LocalDateTime.now());
        rideRepository.save(ride);
        log.info("🚗 Ride started: Ride ID = {}", rideId);
    }

    public void endRide(Long rideId){
        Ride ride = rideRepository.findById(rideId).orElseThrow(() -> new RuntimeException("No Ride Found"));
        ride.setRideStatus(RideStatus.ENDED);
        ride.setEndTime(LocalDateTime.now());
        rideRepository.save(ride);
        log.info("🛑 Ride ended: Ride ID = {}", rideId);
    }

    public void cancelRide(Long rideId){
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("No Ride Found"));
        ride.setRideStatus(RideStatus.CANCELLED);
        rideRepository.save(ride);
        log.warn("🚫 Ride cancelled: Ride ID = {}", rideId);
    }


}
