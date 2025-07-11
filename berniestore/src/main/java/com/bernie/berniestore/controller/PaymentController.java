package com.bernie.berniestore.controller;

import com.bernie.berniestore.dto.PaymentIntentRequestDTO;
import com.bernie.berniestore.dto.PaymentIntentResponseDTO;
import com.bernie.berniestore.service.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final IPaymentService iPaymentService;

    @PostMapping("/create-payment-intent")
    public ResponseEntity<PaymentIntentResponseDTO> createPaymentIntent(
            @RequestBody PaymentIntentRequestDTO paymentIntentRequestDTO) {
        PaymentIntentResponseDTO response = iPaymentService.createPaymentIntent(paymentIntentRequestDTO);
        return ResponseEntity.ok(response);
    }
}
