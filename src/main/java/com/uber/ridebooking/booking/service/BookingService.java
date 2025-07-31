package com.uber.ridebooking.booking.service;

import com.uber.ridebooking.booking.dto.BookingHistoryResponse;
import com.uber.ridebooking.booking.dto.BookingRequest;
import com.uber.ridebooking.booking.dto.BookingResponse;
import com.uber.ridebooking.booking.entity.BookingStatus;

import java.util.List;

public interface BookingService {
    BookingResponse bookRide(BookingRequest request);

    BookingResponse updateRideStatus(Long bookingId, BookingStatus status);

    List<BookingHistoryResponse> getBookingHistoryForRider(Long riderId);

    List<BookingHistoryResponse> getBookingHistoryForDriver(Long driverId);


}