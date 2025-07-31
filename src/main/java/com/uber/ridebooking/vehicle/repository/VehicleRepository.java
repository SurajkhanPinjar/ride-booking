package com.uber.ridebooking.vehicle.repository;

import com.uber.ridebooking.driver.entity.Driver;
import com.uber.ridebooking.vehicle.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByDriverId(long driverId);

    Optional<Vehicle> findByDriver(Driver driver);
}
