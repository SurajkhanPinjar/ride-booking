package com.uber.ridebooking.booking.matching.chain;

import com.uber.ridebooking.booking.dto.BookingRequest;
import com.uber.ridebooking.driver.entity.Driver;
import com.uber.ridebooking.driver.entity.Status;
import com.uber.ridebooking.driver.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class NearestLocationMatcherHandler extends AbstractMatchHandler{

    private final DriverRepository driverRepository;

    @Override
    public Driver handle(BookingRequest request) {
        List<Driver> availableDrivers = driverRepository.findAll().stream()
                .filter(driver -> driver.getStatus().equals(Status.AVAILABLE))
                .toList();

        if (availableDrivers.isEmpty()) {
            log.warn("❌ No AVAILABLE drivers found in the system.");
            return null;
        }

        log.info("🔍 Available Drivers: {}", availableDrivers.size());
        return handle(request, availableDrivers);
    }

    @Override
    public Driver handle(BookingRequest request, List<Driver> drivers) {
        String pickup = request.getPickupLocation();

        List<Driver> nearbyDrivers = drivers.stream()
                .filter(driver -> isNear(driver, pickup))
                .toList();

        if (nearbyDrivers.isEmpty()) {
            log.warn("📍 No nearby drivers found for pickup location: {}", pickup);
            return null;
        }

        log.info("📍 {} drivers found near location: {}", nearbyDrivers.size(), pickup);
        nearbyDrivers.forEach(d ->
                log.info("🧑 Driver: {}, Location: {}", d.getEmail(), d.getLocation())
        );

        return next != null
                ? next.handle(request, nearbyDrivers)
                : nearbyDrivers.getFirst(); // fallback return first
    }

    private boolean isNear(Driver driver, String pickupLocation) {
        // 🚧 Simple mock comparison for now
        String location = driver.getLocation();
        if (null != location)
            return location.equalsIgnoreCase(pickupLocation);
        return false;
    }
}
