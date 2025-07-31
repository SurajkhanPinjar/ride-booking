package com.uber.ridebooking.rating.service;

import com.uber.ridebooking.rating.dto.RatingRequest;
import com.uber.ridebooking.rating.dto.RatingResponse;

public interface RatingService {
    RatingResponse submitRating(RatingRequest ratingRequest);
}
