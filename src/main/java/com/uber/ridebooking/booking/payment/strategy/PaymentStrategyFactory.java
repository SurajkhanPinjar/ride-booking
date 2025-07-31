package com.uber.ridebooking.booking.payment.strategy;

import com.uber.ridebooking.booking.payment.enums.PaymentMethod;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class PaymentStrategyFactory {
    private final Map<String, PaymentStrategy> strategyMap;

    @PostConstruct
    public void logStrategies(){
        strategyMap.forEach((method, impl) ->
                System.out.println("🔧 Registered Payment Strategy: " + method));
    }

    public PaymentStrategy resolve(PaymentMethod method){
        return strategyMap.getOrDefault(method.name(), strategyMap.get("CASH"));
    }
}
