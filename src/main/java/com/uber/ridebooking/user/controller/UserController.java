package com.uber.ridebooking.user.controller;

import com.uber.ridebooking.common.dto.LoginRequest;
import com.uber.ridebooking.user.dto.UserRequest;
import com.uber.ridebooking.user.dto.UserResponse;
import com.uber.ridebooking.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "APIs for user registration, login and profile")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody @Valid UserRequest userRequest) {
        UserResponse user = userService.registerUser(userRequest);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @Operation(summary = "Login user and get JWT token")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest request) {
        String jwt = userService.login(request);
        return ResponseEntity.ok(jwt);
    }

    @Operation(summary = "Get user profile by ID")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getProfile(id));
    }

    @GetMapping("/admin/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminOnly() {
        return "Only Admin can see this.";
    }
}