package com.bernie.berniestore.dto;

import java.math.BigDecimal;

public record OrderItemResponseDTO(String productName, Integer quantity,
                                   BigDecimal price, String imageUrl) {
}
