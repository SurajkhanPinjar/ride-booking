package com.uber.ridebooking.driver.service;

import com.uber.ridebooking.driver.dto.DriverRequest;
import com.uber.ridebooking.driver.entity.Driver;
import com.uber.ridebooking.driver.factory.DriverFactory;
import com.uber.ridebooking.driver.repository.DriverRepository;
import com.uber.ridebooking.user.dto.UserResponse;
import com.uber.ridebooking.user.entity.User;
import com.uber.ridebooking.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService{

    private final DriverRepository driverRepo;
    private final UserRepository userRepository;

    @Override
    public UserResponse registerDriver(DriverRequest driverRequest) {
        // Check if user with same email exists
        if (userRepository.findByEmail(driverRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        // 1. Save User
        User user = User.builder()
                .fullName(driverRequest.getFullName())
                .email(driverRequest.getEmail())
                .password(driverRequest.getPassword())
                .role(User.Role.DRIVER)
                .active(true)
                .build();
        userRepository.save(user);


        // 2. Create Driver using factory
        Driver driver = DriverFactory.createDriver(driverRequest);
        // Optionally associate user in driver if mapped

        // 3. Save Driver
        Driver savedDriver = driverRepo.save(driver);

        // 4. Return response
        return mapToResponse(savedDriver);

    }

    @Override
    public UserResponse getDriverById(Long id) {
        Driver driver = driverRepo.findById(id).orElseThrow(() -> new RuntimeException("No Driver Found"));
        return mapToResponse(driver);
    }

    private UserResponse mapToResponse(Driver driver){
        return UserResponse.builder()
                .id(driver.getId())
                .fullName(driver.getFullName())
                .email(driver.getEmail())
                .role("DRIVER")
                .build();
    }
}
