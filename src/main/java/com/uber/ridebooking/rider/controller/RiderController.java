package com.uber.ridebooking.rider.controller;

import com.uber.ridebooking.rider.dto.RiderRequest;
import com.uber.ridebooking.rider.service.RiderService;
import com.uber.ridebooking.user.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rider")
@RequiredArgsConstructor
@Tag(name = "Rider Module", description = "Endpoints for Driver registration and profile")
public class RiderController {

    private final RiderService riderService;

    @PostMapping("/register")
    @Operation(summary = "Register a New Rider")
    public ResponseEntity<UserResponse> registerRider (@RequestBody RiderRequest riderRequest){
        return ResponseEntity.ok(riderService.registerRider(riderRequest));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Rider profile by ID")
    public ResponseEntity<UserResponse> getRiderById(@PathVariable Long id){
        return ResponseEntity.ok(riderService.getRiderById(id));
    }

}