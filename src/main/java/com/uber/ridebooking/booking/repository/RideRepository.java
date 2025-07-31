package com.uber.ridebooking.booking.repository;

import com.uber.ridebooking.booking.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<Ride, Long> {

}
