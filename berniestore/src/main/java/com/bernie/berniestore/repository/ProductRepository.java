package com.bernie.berniestore.repository;

import com.bernie.berniestore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}