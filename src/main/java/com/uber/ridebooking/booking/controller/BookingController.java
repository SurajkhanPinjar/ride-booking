package com.uber.ridebooking.booking.controller;

import com.uber.ridebooking.booking.dto.BookingHistoryResponse;
import com.uber.ridebooking.booking.dto.BookingRequest;
import com.uber.ridebooking.booking.dto.BookingResponse;
import com.uber.ridebooking.booking.entity.BookingStatus;
import com.uber.ridebooking.booking.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
@Tag(name = "", description = "")
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/book-ride")
    @Operation(summary = "Book Ride as per Rating, near by Location driver")
    public ResponseEntity<BookingResponse> bookRide(@RequestBody BookingRequest bookingRequest){
        BookingResponse bookingResponse  = bookingService.bookRide(bookingRequest);
        return ResponseEntity.ok(bookingResponse);
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Update Book Ride status by ID")
    public ResponseEntity<BookingResponse> updateRideStatus(
            @PathVariable Long id,
            @RequestParam("status") BookingStatus status
    ) {
        BookingResponse response = bookingService.updateRideStatus(id, status);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/history/rider/{riderId}")
    @Operation(summary = "Booking History for Rider")
    public List<BookingHistoryResponse> getBookingHistoryForRider(@PathVariable Long riderId) {
        return bookingService.getBookingHistoryForRider(riderId);
    }

    @GetMapping("/history/driver/{driverId}")
    @Operation(summary = "Booking History for Driver")
    public List<BookingHistoryResponse> getBookingHistoryForDriver(@PathVariable Long driverId) {
        return bookingService.getBookingHistoryForDriver(driverId);
    }
}
