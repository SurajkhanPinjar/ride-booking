package com.uber.ridebooking.booking.payment.entity;

import com.uber.ridebooking.booking.entity.Booking;
import com.uber.ridebooking.booking.payment.enums.PaymentMethod;
import com.uber.ridebooking.booking.payment.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    private LocalDateTime paymentTime;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
}
