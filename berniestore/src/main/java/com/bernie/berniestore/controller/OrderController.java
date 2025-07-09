package com.bernie.berniestore.controller;

import com.bernie.berniestore.dto.OrderRequestDTO;
import com.bernie.berniestore.service.implement.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final IOrderService iOrderService;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        iOrderService.createOrder(orderRequestDTO);
        return ResponseEntity.ok("Order created successfully");
    }
}
