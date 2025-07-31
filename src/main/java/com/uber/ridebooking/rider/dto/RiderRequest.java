package com.uber.ridebooking.rider.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RiderRequest {
    private String fullName;
    private String email;
    private String password;
    private String phone;
    private String location;
}