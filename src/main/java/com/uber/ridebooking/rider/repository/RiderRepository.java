package com.uber.ridebooking.rider.repository;

import com.uber.ridebooking.rider.entity.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RiderRepository extends JpaRepository<Rider, Long> {
    Optional<Rider> findByEmail(String email);
}
