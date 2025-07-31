package com.uber.ridebooking.driver.service;

import com.uber.ridebooking.driver.dto.DriverRequest;
import com.uber.ridebooking.user.dto.UserResponse;

public interface DriverService {
    UserResponse registerDriver(DriverRequest driverRequest);
    UserResponse getDriverById(Long id);
}
