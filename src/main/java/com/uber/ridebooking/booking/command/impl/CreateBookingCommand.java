package com.uber.ridebooking.booking.command.impl;

import com.uber.ridebooking.booking.command.BookingCommand;
import com.uber.ridebooking.booking.dto.BookingRequest;
import com.uber.ridebooking.booking.entity.Booking;
import com.uber.ridebooking.booking.entity.BookingStatus;
import com.uber.ridebooking.driver.entity.Driver;
import com.uber.ridebooking.rider.entity.Rider;
import com.uber.ridebooking.rider.repository.RiderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateBookingCommand implements BookingCommand {

    private final RiderRepository riderRepository;

    @Override
    public Booking execute(BookingRequest request, Driver driver) {
        Rider rider = riderRepository.findById(request.getRiderId())
                .orElseThrow(() -> new RuntimeException("No Rider Found"));
        return Booking.builder()
                .source(request.getSource())
                .destination(request.getDestination())
                .pickupLocation(request.getPickupLocation())
                .rider(rider)
                .driver(driver)
                .bookingTime(LocalDateTime.now())
                .status(BookingStatus.CONFIRMED)
                .build();
    }
}
