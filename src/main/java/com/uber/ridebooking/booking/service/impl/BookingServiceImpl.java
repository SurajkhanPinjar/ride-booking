package com.uber.ridebooking.booking.service.impl;

import com.uber.ridebooking.booking.command.BookingCommand;
import com.uber.ridebooking.booking.dto.BookingHistoryResponse;
import com.uber.ridebooking.booking.dto.BookingRequest;
import com.uber.ridebooking.booking.dto.BookingResponse;
import com.uber.ridebooking.booking.entity.Booking;
import com.uber.ridebooking.booking.entity.BookingStatus;
import com.uber.ridebooking.booking.enums.RideStatus;
import com.uber.ridebooking.booking.matching.chain.MatchHandlerChain;
import com.uber.ridebooking.booking.matching.chain.NearestLocationMatcherHandler;
import com.uber.ridebooking.booking.matching.chain.RatingMatcherHandler;
import com.uber.ridebooking.booking.matching.chain.VehicleTypeMatcherHandler;
import com.uber.ridebooking.booking.observer.BookingEventPublisher;
import com.uber.ridebooking.booking.repository.BookingRepository;
import com.uber.ridebooking.booking.service.BookingService;
import com.uber.ridebooking.driver.entity.Driver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingCommand bookingCommand;
    private final BookingRepository bookingRepository;

    private final NearestLocationMatcherHandler nearestLocationMatcherHandler;
    private final VehicleTypeMatcherHandler vehicleTypeMatcherHandler;
    private final RatingMatcherHandler ratingMatcherHandler;

    private final MatchHandlerChain matchHandlerChain;

    private final BookingEventPublisher bookingEventPublisher;

    @Override
    public BookingResponse bookRide(BookingRequest request) {

        // 1. Setup Matcher handler
        nearestLocationMatcherHandler.setNext(vehicleTypeMatcherHandler);
        vehicleTypeMatcherHandler.setNext(ratingMatcherHandler);

        // 2. Find Matching handler
        Driver matchedDriver = matchHandlerChain.getMatchedDriver(request);

        if(matchedDriver == null)
            return BookingResponse.builder()
                    .status("FAILURE")
                    .message("No Suitable Driver found")
                    .build();

        // 3.  Create booking entity using command
        Booking booking = bookingCommand.execute(request, matchedDriver);
        Booking savedBooking = bookingRepository.save(booking);

        // Trigger Observer Notification
        bookingEventPublisher.notifyObservers(savedBooking);

        // 4. Return Success response
        return BookingResponse.builder()
                .status("SUCCESS")
                .message("Driver matched and booking confirmed")
                .bookingId(savedBooking.getId())
                .driverName(matchedDriver.getFullName())
                .vehicleType(matchedDriver.getVehicle().getType().toString())
                .build();
    }

    @Override
    public BookingResponse updateRideStatus(Long bookingId, BookingStatus status) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(status);

        switch (status){
            case STARTED -> booking.setBookingTime(LocalDateTime.now());
            case COMPLETED -> booking.setCompletedAt(LocalDateTime.now());
            case CANCELLED -> booking.setCompletedAt(LocalDateTime.now());
        }

        Booking updated = bookingRepository.save(booking);

        // Observer when updated status
        bookingEventPublisher.notifyObserversOnStatusChange(updated);

        return BookingResponse.builder()
                .bookingId(bookingId)
                .driverName(updated.getDriver().getFullName())
                .status("SUCCESS")
                .message("Ride status updated to " + status)
                .build();
    }

    @Override
    public List<BookingHistoryResponse> getBookingHistoryForRider(Long riderId) {
        return bookingRepository.findByRiderIdOrderByBookingTimeDesc(riderId)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingHistoryResponse> getBookingHistoryForDriver(Long driverId) {
        return bookingRepository.findByDriverIdOrderByBookingTimeDesc(driverId)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private BookingHistoryResponse convertToResponse(Booking booking){
        return BookingHistoryResponse.builder()
                .bookingId(booking.getId())
                .driverId(booking.getDriver().getId())
                .riderId(booking.getRider().getId())
                .pickupLocation(booking.getPickupLocation())
                .dropLocation(booking.getDestination())
                .bookedAt(booking.getBookingTime())
                .bookingStatus(booking.getStatus())
                .build();
    }
}
