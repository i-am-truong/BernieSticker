package com.bernie.berniestore.service.implement;

import com.bernie.berniestore.constants.ApplicationConstants;
import com.bernie.berniestore.dto.OrderItemResponseDTO;
import com.bernie.berniestore.dto.OrderRequestDTO;
import com.bernie.berniestore.dto.OrderResponseDTO;
import com.bernie.berniestore.entity.Customer;
import com.bernie.berniestore.entity.Order;
import com.bernie.berniestore.entity.OrderItem;
import com.bernie.berniestore.entity.Product;
import com.bernie.berniestore.exception.ResourceNotFoundException;
import com.bernie.berniestore.repository.OrderRepository;
import com.bernie.berniestore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImplement implements IOrderService{

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ProfileServiceImplement profileServiceImplement;

    @Override
    public void createOrder(OrderRequestDTO orderRequest) {
        Customer customer = profileServiceImplement.getAuthenticatedCustomer();
        Order order = new Order();
        order.setCustomer(customer);
        BeanUtils.copyProperties(orderRequest,order);
        order.setOrderStatus(ApplicationConstants.ORDER_STATUS_CREATED);
        List<OrderItem> orderItems = orderRequest.items().stream().map(item -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            Product product = productRepository.findById(item.productId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product", "Product ID", item.productId().toString()));
            orderItem.setProduct(product);
            orderItem.setQuantity(item.quantity());
            orderItem.setPrice(item.price());
            return orderItem;
        }).toList();
        order.setOrderItems(orderItems);
        orderRepository.save(order);
    }

    @Override
    public List<OrderResponseDTO> getCustomerOrders() {
        Customer customer = profileServiceImplement.getAuthenticatedCustomer();
        List<Order> orders = orderRepository.findOrdersByCustomerWithNativeQuery(customer.getCustomerId());
        return  orders.stream().map(this::mapToOrderResponseDTO).collect(Collectors.toList());
    }

    @Override
    public List<OrderResponseDTO> getAllPendingOrders() {
        List<Order> orders = orderRepository.findOrdersByStatusWithNativeQuery(ApplicationConstants.ORDER_STATUS_CREATED);
        return orders.stream().map(this::mapToOrderResponseDTO).collect(Collectors.toList());
    }

    @Override
    public void updateOrderStatus(Long orderId, String orderStatus) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        orderRepository.updateOrderStatus(orderId,orderStatus,email);
    }

    private OrderResponseDTO mapToOrderResponseDTO(Order order) {
        List<OrderItemResponseDTO> itemDTOs = order.getOrderItems().stream()
                .map(this::mapToOrderItemResponseDTO)
                .toList();
        return new OrderResponseDTO(order.getOrderId(),
                order.getOrderStatus(), order.getTotalPrice(), order.getCreatedAt().toString(), itemDTOs);
    }

    private OrderItemResponseDTO mapToOrderItemResponseDTO(OrderItem orderItem) {
        return new OrderItemResponseDTO(
                orderItem.getProduct().getName(), orderItem.getQuantity(),
                orderItem.getPrice(), orderItem.getProduct().getImageUrl());

    }
}
