package com.uber.ridebooking.booking.payment.controller;

import com.uber.ridebooking.booking.payment.dto.PaymentRequest;
import com.uber.ridebooking.booking.payment.dto.PaymentResponse;
import com.uber.ridebooking.booking.payment.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
@Tag(name = "Payment Controller", description = "")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/make-payments")
    @Operation(summary = "Make Payment")
    public ResponseEntity<PaymentResponse> makePayment(@RequestBody PaymentRequest paymentRequest){
        return ResponseEntity.ok(paymentService.makePayment(paymentRequest));
    }
}
