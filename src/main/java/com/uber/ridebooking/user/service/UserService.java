package com.uber.ridebooking.user.service;

import com.uber.ridebooking.common.dto.LoginRequest;
import com.uber.ridebooking.user.dto.UserRequest;
import com.uber.ridebooking.user.dto.UserResponse;

public interface UserService {
    UserResponse registerUser(UserRequest request);
    String login(LoginRequest request);
    UserResponse getProfile(Long userId);
}
