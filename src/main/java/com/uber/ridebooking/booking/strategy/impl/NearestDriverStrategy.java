package com.uber.ridebooking.booking.strategy.impl;

import com.uber.ridebooking.booking.strategy.BookingStrategy;
import com.uber.ridebooking.driver.entity.Driver;
import com.uber.ridebooking.driver.entity.Status;
import com.uber.ridebooking.driver.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NearestDriverStrategy implements BookingStrategy {

    private final DriverRepository driverRepository;

    @Override
    public Driver findDriver(String source) {
        List<Driver> availableDriver = driverRepository.findByStatus(Status.AVAILABLE);
        return availableDriver.isEmpty() ? null : availableDriver.getFirst();
    }
}
