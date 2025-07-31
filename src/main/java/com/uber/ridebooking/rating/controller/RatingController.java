package com.uber.ridebooking.rating.controller;

import com.uber.ridebooking.rating.dto.RatingRequest;
import com.uber.ridebooking.rating.dto.RatingResponse;
import com.uber.ridebooking.rating.service.RatingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ratings")
@RequiredArgsConstructor
@Tag(name = "Rate a Driver", description = "Rider can give rate to Driver for a Ride")
public class RatingController {

    private final RatingService ratingService;

    @PostMapping
    @Operation(description = "Submit Rating")
    public ResponseEntity<RatingResponse> submitRating(@RequestBody RatingRequest request){
        RatingResponse response = ratingService.submitRating(request);
        return ResponseEntity.ok(response);
    }
}
