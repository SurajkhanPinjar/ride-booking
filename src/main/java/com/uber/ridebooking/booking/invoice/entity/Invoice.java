package com.uber.ridebooking.booking.invoice.entity;

import com.uber.ridebooking.booking.payment.enums.PaymentMethod;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookingId;
    private String driverName;
    private String riderName;

    private String pickupLocation;
    private String dropLocation;

    private double fare;
    private double distance;
    private PaymentMethod payment;

    private LocalDateTime invoiceDateAndTime;

}
