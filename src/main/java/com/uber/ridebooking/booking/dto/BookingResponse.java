package com.uber.ridebooking.booking.dto;

import com.uber.ridebooking.booking.entity.BookingStatus;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponse {

    private String status;
    private String message;
    private Long bookingId;
    private String driverName;
    private String vehicleType;

}