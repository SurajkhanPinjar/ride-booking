package com.uber.ridebooking.user.factory;

import com.uber.ridebooking.user.dto.UserRequest;
import com.uber.ridebooking.user.entity.User;

public class Userfactory {

    public static User createUser(UserRequest request){
        User user = User.builder().
                fullName(request.getFullName())
                .email(request.getEmail())
                .password(request.getPassword())
                .phone(request.getPhone())
                .role(User.Role.valueOf(request.getRole().toUpperCase()))
                .build();
        return user;
    }
}
