package com.uber.ridebooking.booking.matching.chain;

import com.uber.ridebooking.booking.dto.BookingRequest;
import com.uber.ridebooking.driver.entity.Driver;
import com.uber.ridebooking.driver.entity.Status;
import com.uber.ridebooking.driver.repository.DriverRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class MatchHandlerChain {
    private final NearestLocationMatcherHandler nearestLocationMatcherHandler;
    private final VehicleTypeMatcherHandler vehicleTypeMatcherHandler;
    private final RatingMatcherHandler ratingMatcherHandler;

    private final DriverRepository driverRepository;

    @PostConstruct
    public void setupChain() {
        nearestLocationMatcherHandler.setNext(vehicleTypeMatcherHandler);
        vehicleTypeMatcherHandler.setNext(ratingMatcherHandler);
        log.info("🔗 MatchHandler Chain setup: Nearest → VehicleType → Rating");
    }

    public Driver getMatchedDriver(BookingRequest request){
        List<Driver> availableDrivers = driverRepository.findByStatus(Status.AVAILABLE);
        if (availableDrivers.isEmpty()) {
            log.warn("❌ No available drivers in the system");
            return null;
        }

        log.info("🔍 Total available drivers: {}", availableDrivers.size());
        availableDrivers.forEach(d ->
                log.info("🧑 Driver: {}, Location: {}, VehicleType: {}, Rating: {}",
                        d.getEmail(),
                        d.getLocation(),
                        d.getVehicle().getType(),
                        d.getRating())
        );
        // Step 2: Run chain
        return nearestLocationMatcherHandler.handle(request, availableDrivers);
    }
}
