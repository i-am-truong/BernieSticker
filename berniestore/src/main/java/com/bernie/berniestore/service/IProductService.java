package com.bernie.berniestore.service;

import com.bernie.berniestore.dto.ProductDTO;

import java.util.List;

public interface IProductService {

    List<ProductDTO> getAllProducts();
}
