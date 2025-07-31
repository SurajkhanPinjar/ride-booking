package com.uber.ridebooking.booking.matching.chain;

import com.uber.ridebooking.booking.dto.BookingRequest;
import com.uber.ridebooking.driver.entity.Driver;
import com.uber.ridebooking.driver.repository.DriverRepository;
import com.uber.ridebooking.vehicle.dto.VehicleType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VehicleTypeMatcherHandler extends AbstractMatchHandler{

    @Override
    public Driver handle(BookingRequest request, List<Driver> filteredDrivers) {

        VehicleType preferredVehicleType = request.getPreferredVehicleType();

        List<Driver> matched = filteredDrivers.stream()
                .filter(driver -> driver.getVehicle().getType().equals(preferredVehicleType))
                .toList();
        if (matched.isEmpty()) return null;

        return next != null
                ?  ((AbstractMatchHandler) next).handle(request, matched)
                : matched.getFirst();
    }
}
