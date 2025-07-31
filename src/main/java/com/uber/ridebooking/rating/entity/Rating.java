package com.uber.ridebooking.rating.entity;

import com.uber.ridebooking.booking.entity.Booking;
import com.uber.ridebooking.driver.entity.Driver;
import com.uber.ridebooking.rider.entity.Rider;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double rating;

    private String comment;

    @OneToOne
    @JoinColumn(name = "drive_id")
    private Driver driver;

    @OneToOne
    @JoinColumn(name = "rider_id")
    private Rider rider;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    private LocalDateTime ratedAt;

}
