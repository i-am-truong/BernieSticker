package com.bernie.berniestore.service.implement;

import com.bernie.berniestore.dto.OrderRequestDTO;

public interface IOrderService {
    void createOrder(OrderRequestDTO orderRequest);
}
