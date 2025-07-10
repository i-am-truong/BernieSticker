package com.bernie.berniestore.dto;

import java.math.BigDecimal;
import java.util.List;

public record OrderResponseDTO(Long orderId, String status, BigDecimal totalPrice, String createdAt,
                               List<OrderItemResponseDTO> items) {
}
