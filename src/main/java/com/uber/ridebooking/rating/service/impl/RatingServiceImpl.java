package com.uber.ridebooking.rating.service.impl;

import com.uber.ridebooking.booking.entity.Booking;
import com.uber.ridebooking.booking.repository.BookingRepository;
import com.uber.ridebooking.driver.entity.Driver;
import com.uber.ridebooking.driver.repository.DriverRepository;
import com.uber.ridebooking.rating.dto.RatingRequest;
import com.uber.ridebooking.rating.dto.RatingResponse;
import com.uber.ridebooking.rating.entity.Rating;
import com.uber.ridebooking.rating.repository.RatingRepository;
import com.uber.ridebooking.rating.service.RatingService;
import com.uber.ridebooking.rider.entity.Rider;
import com.uber.ridebooking.rider.repository.RiderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final DriverRepository driverRepository;
    private final RiderRepository riderRepository;
    private final BookingRepository bookingRepository;
    private final RatingRepository ratingRepository;

    @Override
    public RatingResponse submitRating(RatingRequest ratingRequest) {

        // to check duplicate rating by booking id
        if (ratingRepository.existsByBookingId(ratingRequest.getBookingId()))
            return RatingResponse.builder()
                    .status("FAILURE")
                    .message("Rating already submitted for this booking")
                    .build();

        Booking booking = bookingRepository.findById(ratingRequest.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking Not Found"));

        Driver driver = driverRepository.findById(ratingRequest.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver Not Found"));

        Rider rider = riderRepository.findById(ratingRequest.getRiderId())
                .orElseThrow(() -> new RuntimeException("Rider Not Found"));

        // Create and save the data
        Rating rating  = Rating.builder()
                .booking(booking)
                .rider(rider)
                .driver(driver)
                .rating(ratingRequest.getRating())
                .comment(ratingRequest.getComment())
                .ratedAt(LocalDateTime.now())
                .build();
        //Persisting Rating
        Rating savedRating = ratingRepository.save(rating);

        //Update driver's Rating
        double totalRating = driver.getTotalRatingSum() + ratingRequest.getRating();
        int totalRatingCount = driver.getTotalRatingsCount() + 1;
        double avgRating = totalRating/totalRatingCount ;

        driver.setRating(avgRating);
        driver.setTotalRatingsCount(totalRatingCount);
        driver.setTotalRatingSum(totalRating);

        driverRepository.save(driver);

        return RatingResponse.builder()
                .status("SUCCESS")
                .message("Rating submitted successfully")
                .ratingGiven(ratingRequest.getRating())
                .updatedDriverRating(avgRating)
                .build();
    }
}
