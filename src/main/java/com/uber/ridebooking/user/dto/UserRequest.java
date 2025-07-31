package com.uber.ridebooking.user.dto;

import lombok.Data;

@Data
public class UserRequest {

    private String fullName;
    private String email;
    private String password;
    private String phone;
    private String role; // RIDER / DRIVER

}
