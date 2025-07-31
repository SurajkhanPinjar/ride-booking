package com.uber.ridebooking.booking.repository;

import com.uber.ridebooking.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByRiderIdOrderByBookingTimeDesc(Long riderId);
    List<Booking> findByDriverIdOrderByBookingTimeDesc(Long driverId);
}
