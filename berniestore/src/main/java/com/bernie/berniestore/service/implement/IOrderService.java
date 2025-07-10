package com.bernie.berniestore.service.implement;

import com.bernie.berniestore.dto.OrderRequestDTO;
import com.bernie.berniestore.dto.OrderResponseDTO;

import java.util.List;

public interface IOrderService {
    void createOrder(OrderRequestDTO orderRequest);

    List<OrderResponseDTO> getCustomerOrders();

    List<OrderResponseDTO> getAllPendingOrders();

    void updateOrderStatus(Long orderId, String orderStatus);
}
