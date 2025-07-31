//package com.uber.ridebooking.booking.service;
//
//import com.uber.ridebooking.booking.dto.BookingRequest;
//import com.uber.ridebooking.booking.dto.BookingResponse;
//import com.uber.ridebooking.booking.entity.Booking;
//import com.uber.ridebooking.booking.entity.BookingStatus;
//import com.uber.ridebooking.booking.repository.BookingRepository;
//import com.uber.ridebooking.booking.strategy.BookingStrategy;
//import com.uber.ridebooking.driver.entity.Driver;
//import com.uber.ridebooking.driver.entity.Status;
//import com.uber.ridebooking.driver.repository.DriverRepository;
//import com.uber.ridebooking.rider.repository.RiderRepository;
//import com.uber.ridebooking.user.entity.User;
//import com.uber.ridebooking.user.repository.UserRepository;
//import com.uber.ridebooking.vehicle.entity.Vehicle;
//import com.uber.ridebooking.vehicle.repository.VehicleRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import java.awt.print.Book;
//import java.time.LocalDateTime;
//
//@Component
//@RequiredArgsConstructor
//public class BookingService {
//    private final BookingRepository bookingRepository;
//    private final UserRepository userRepository;
//    private final DriverRepository driverRepository;
//    private final VehicleRepository vehicleRepository;
//    private final BookingStrategy bookingStrategy;
//
//    public BookingResponse createBooking(BookingRequest request){
//        User rider = userRepository.findById(request.getRiderId()).orElseThrow(() -> new RuntimeException("User Not Found"));
//
//        Driver driver = bookingStrategy.findDriver(request.getSource());
//        if(driver == null) throw new RuntimeException("No Drivers Available");
//
//        Vehicle vehicle = vehicleRepository.findByDriver(driver).orElseThrow(() -> new RuntimeException("Vehicle Not Found"));
//
//        Double fare = calculateFare(request.getSource(), request.getDestination());
//        Booking booking = Booking.builder()
//                .rider(rider)
//                .driver(driver)
//                .vehicle(vehicle)
//                .source(request.getSource())
//                .destination(request.getDestination())
//                .fare(fare)
//                .status(BookingStatus.BOOKED)
//                .bookedAt(LocalDateTime.now())
//                .build();
//        bookingRepository.save(booking);
//
//        // Update driver status
//        driver.setStatus(Status.ON_TRIP);
//        driverRepository.save(driver);
//
//        return BookingResponse.builder()
//                .bookingId(booking.getId())
//                .driverName(driver.getFullName())
////                .vehicleNumber(vehicle.getVe)
//                .fare(fare)
//                .status(booking.getStatus())
//                .build();
//    }
//
//    public void startRide(Long bookingId){
//        Booking booking =  bookingRepository.findById(bookingId).orElseThrow(() -> new RuntimeException("Booking Not Found"));
//        booking.setStatus(BookingStatus.IN_PROGRESS);
//        booking.setStartedAt(LocalDateTime.now());
//
//        Driver driver = booking.getDriver();
//        driver.setStatus(Status.ON_TRIP);
//        driverRepository.save(driver);
//
//        bookingRepository.save(booking);
//
//    }
//
//    public void completeRide(Long bookingId){
//        Booking booking = bookingRepository.findById(bookingId). orElseThrow(() -> new RuntimeException("Booking not Found"));
//        booking.setStatus(BookingStatus.COMPLETED);
////        booking.setBookedAt(LocalDateTime.now());
//
//        Driver driver = booking.getDriver();
//        driver.setStatus(Status.AVAILABLE);
//        driverRepository.save(driver);
//
//        bookingRepository.save(booking);
//    }
//
//    public void cancelRide(Long bookingId) {
//        Booking booking = bookingRepository.findById(bookingId)
//                .orElseThrow(() -> new RuntimeException("Booking not found"));
//
//        booking.setStatus(BookingStatus.CANCELLED);
//
//        Driver driver = booking.getDriver();
//        driver.setStatus(Status.AVAILABLE);
//        driverRepository.save(driver);
//
//        bookingRepository.save(booking);
//    }
//
//    private Double calculateFare(String source, String destination){
//        return 100.0; //TODO dummy fare
//    }
//
//}
