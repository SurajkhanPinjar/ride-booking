package com.uber.ridebooking.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//import javax.management.relation.Role;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder.Default
    private boolean active = true;

    public enum Role {
        RIDER, DRIVER, ADMIN
    }

}
