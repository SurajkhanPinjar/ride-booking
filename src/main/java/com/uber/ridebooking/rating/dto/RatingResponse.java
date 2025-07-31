package com.uber.ridebooking.rating.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RatingResponse {
    private String status;
    private String message;
    private Double ratingGiven;
    private double updatedDriverRating;
}
