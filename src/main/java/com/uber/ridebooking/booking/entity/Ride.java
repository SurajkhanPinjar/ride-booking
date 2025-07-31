package com.uber.ridebooking.booking.entity;

import com.uber.ridebooking.booking.enums.RideStatus;
import com.uber.ridebooking.vehicle.dto.VehicleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long driverId;

    private String pickupLocation;

    private String dropLocation;

    @Enumerated(EnumType.STRING)
    private RideStatus rideStatus;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Double price;

    @Column
    private Double rating;


}
