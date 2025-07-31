package com.uber.ridebooking.user.service;

import com.uber.ridebooking.common.dto.LoginRequest;
import com.uber.ridebooking.user.dto.UserRequest;
import com.uber.ridebooking.user.dto.UserResponse;
import com.uber.ridebooking.user.entity.User;
import com.uber.ridebooking.user.factory.Userfactory;
import com.uber.ridebooking.user.repository.UserRepository;
import com.uber.ridebooking.common.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepo;
    private final JwtUtil jwtUtil;

    @Override
    public UserResponse registerUser(UserRequest request) {
        Optional<User> existingUser =  userRepo.findByEmail(request.getEmail());
        if(existingUser.isPresent()) throw new RuntimeException("User Already Existing");
        User user = Userfactory.createUser(request);
        return mapToResponse(userRepo.save(user));
    }

    @Override
    public String login(LoginRequest request) {
        User user = userRepo.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("Invalid Email or Password"));

        if(!user.getPassword().equals(request.getPassword()))  throw new RuntimeException("Invalid Email or Password");

        return jwtUtil.generateToken(user);
    }

    @Override
    public UserResponse getProfile(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("No User Found :("));
        return mapToResponse(user);
    }

    private UserResponse mapToResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }
}
