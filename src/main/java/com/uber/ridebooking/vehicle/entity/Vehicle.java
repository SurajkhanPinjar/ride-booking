package com.uber.ridebooking.vehicle.entity;

import com.uber.ridebooking.driver.entity.Driver;
import com.uber.ridebooking.vehicle.dto.VehicleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licensePlate;

    @Enumerated(EnumType.STRING)
    private VehicleType type;

    private String model;

    private String color;

    private int capacity;

    @OneToOne(mappedBy = "vehicle")
    private Driver driver;
}