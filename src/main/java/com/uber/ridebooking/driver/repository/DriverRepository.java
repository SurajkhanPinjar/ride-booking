package com.uber.ridebooking.driver.repository;

import com.uber.ridebooking.driver.entity.Driver;
import com.uber.ridebooking.driver.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findByEmail(String email);

    List<Driver> findByStatus(Status status);
}
