package com.bernie.berniestore.service.implement;

import com.bernie.berniestore.dto.ProductDTO;
import com.bernie.berniestore.entity.Product;
import com.bernie.berniestore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImplement implements IProductService{
    private final ProductRepository productRepository;

    @Cacheable("products")
    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(this::transformToDTO).collect(Collectors.toList());
    }

    private ProductDTO transformToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);
        productDTO.setProductId(product.getId());
        return productDTO;
    }
}
