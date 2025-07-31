package com.uber.ridebooking.rider.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "riders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private String phone;

    private String location;

    @Builder.Default
    private boolean active = true;
}