package com.uber.ridebooking.booking.matching.chain;

import com.uber.ridebooking.booking.dto.BookingRequest;
import com.uber.ridebooking.driver.entity.Driver;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class RatingMatcherHandler extends AbstractMatchHandler{

    private static final double DEFAULT_MIN_RATING = 4.0;

    @Override
    public Driver handle(BookingRequest request, List<Driver> filteredDrivers) {

        List<Driver> highRated = filteredDrivers.stream()
                .filter(driver -> driver.getRating() == DEFAULT_MIN_RATING)
                .sorted(Comparator.comparingDouble(Driver::getRating).reversed())
                .toList();
        if (highRated.isEmpty()) return null;

        return highRated.getFirst();
    }
}
