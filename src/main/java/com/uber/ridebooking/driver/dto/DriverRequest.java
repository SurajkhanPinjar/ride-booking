package com.uber.ridebooking.driver.dto;

import com.uber.ridebooking.vehicle.dto.VehicleType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverRequest {

    private String fullName;

    private String email;

    private String password;

    private String phone;

    private String licenseNumber;

    private String vehicleNumber;

    private String vehicleModel;

    private String vehicleColor;

    private String location;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;
}