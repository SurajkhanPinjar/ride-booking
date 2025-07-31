package com.uber.ridebooking.booking.dto;

import com.uber.ridebooking.booking.entity.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class BookingHistoryResponse {

    private Long bookingId;
    private Long driverId;
    private Long riderId;
    private String pickupLocation;
    private String dropLocation;
    private BookingStatus bookingStatus;
    private LocalDateTime bookedAt;

}
