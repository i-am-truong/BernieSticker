package com.bernie.berniestore.dto;


import java.math.BigDecimal;

public record OrderItemDTO(Long productId, Integer quantity, BigDecimal price) {
    // No additional methods or fields needed; the record automatically provides getters and a constructor.
}
