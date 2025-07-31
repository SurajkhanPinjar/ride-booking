package com.uber.ridebooking.rating.dto;

import lombok.Data;

@Data
public class RatingRequest {
    private Long riderId;
    private Long driverId;
    private Long bookingId;
    private String comment;
    private Double rating;
}
