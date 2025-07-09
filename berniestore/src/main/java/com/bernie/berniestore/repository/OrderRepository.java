package com.bernie.berniestore.repository;

import com.bernie.berniestore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
