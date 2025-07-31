package com.uber.ridebooking.rating.repository;

import com.uber.ridebooking.rating.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    boolean existsByBookingId(long bookingId);
}
