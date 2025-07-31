package com.uber.ridebooking.booking.entity;

import com.uber.ridebooking.booking.payment.entity.Payment;
import com.uber.ridebooking.driver.entity.Driver;
import com.uber.ridebooking.rider.entity.Rider;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String source;

    private String destination;

    private String pickupLocation;

    private LocalDateTime bookingTime;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "rider_id")
    private Rider rider;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private LocalDateTime tripStartTime;
    private LocalDateTime tripEndTime;
    private Double fare;

    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
    private Payment payment;
}