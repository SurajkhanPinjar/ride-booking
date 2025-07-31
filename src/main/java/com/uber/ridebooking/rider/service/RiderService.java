package com.uber.ridebooking.rider.service;

import com.uber.ridebooking.rider.dto.RiderRequest;
import com.uber.ridebooking.user.dto.UserResponse;

public interface RiderService {
    UserResponse registerRider(RiderRequest riderRequest);
    UserResponse getRiderById(Long id);
}
