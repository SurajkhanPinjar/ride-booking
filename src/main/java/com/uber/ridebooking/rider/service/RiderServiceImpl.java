package com.uber.ridebooking.rider.service;

import com.uber.ridebooking.rider.dto.RiderRequest;
import com.uber.ridebooking.rider.entity.Rider;
import com.uber.ridebooking.rider.factory.RiderFactory;
import com.uber.ridebooking.rider.repository.RiderRepository;
import com.uber.ridebooking.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RiderServiceImpl implements RiderService {

    private final RiderRepository riderRepo;

    @Override
    public UserResponse registerRider(RiderRequest riderRequest) {
        if(riderRepo.findByEmail(riderRequest.getEmail()).isPresent()) throw new RuntimeException("User Already Exists ");
        return mapToObject(riderRepo.save(RiderFactory.createRider(riderRequest)));
    }

    @Override
    public UserResponse getRiderById(Long id) {
        Rider rider = riderRepo.findById(id).orElseThrow(() -> new RuntimeException("No User Found"));
        return mapToObject(rider);
    }

    private UserResponse mapToObject(Rider rider){
        return UserResponse.builder()
                .id(rider.getId())
                .fullName(rider.getFullName())
                .email(rider.getEmail())
                .build();
    }
}
