package com.bernie.berniestore.controller;

import com.bernie.berniestore.dto.ProductDTO;
import com.bernie.berniestore.service.implement.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService iProductService;

    @GetMapping
    public List<ProductDTO> getProducts() throws InterruptedException  {  //DTO Pattern
        return iProductService.getAllProducts();
    }
}