package com.uber.ridebooking.rider.factory;

import com.uber.ridebooking.rider.dto.RiderRequest;
import com.uber.ridebooking.rider.entity.Rider;

public class RiderFactory {

    public static Rider createRider(RiderRequest request) {
        return Rider.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(request.getPassword())
                .phone(request.getPhone())
                .location(request.getLocation())
                .active(true)
                .build();
    }
}