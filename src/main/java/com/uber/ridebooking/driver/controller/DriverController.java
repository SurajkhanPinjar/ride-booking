package com.uber.ridebooking.driver.controller;

import com.uber.ridebooking.driver.dto.DriverRequest;
import com.uber.ridebooking.driver.service.DriverService;
import com.uber.ridebooking.user.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/drivers")
@RequiredArgsConstructor
@Tag(name = "Driver Module", description = "Endpoints for Driver registration and profile")
public class DriverController {

    private final DriverService driverService;

    @PostMapping("/register")
    @Operation(summary = "Register a New Driver")
    public ResponseEntity<UserResponse> registerDriver (@RequestBody DriverRequest driverRequest){
        return ResponseEntity.ok(driverService.registerDriver(driverRequest));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Driver profile by ID")
    public ResponseEntity<UserResponse> getDriverById(@PathVariable Long id){
        return ResponseEntity.ok(driverService.getDriverById(id));
    }

}
