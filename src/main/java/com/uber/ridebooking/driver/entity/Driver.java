package com.uber.ridebooking.driver.entity;

import com.uber.ridebooking.vehicle.entity.Vehicle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private String phone;

    private String licenseNumber;

//    private boolean available;
    private String location;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;

    @Builder.Default
    private boolean active = true;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Double rating; // e.g., 4.7\\

    private Double totalRatingSum;        // Sum of all ratings received

    private Integer totalRatingsCount;    // Total number of ratings


}
