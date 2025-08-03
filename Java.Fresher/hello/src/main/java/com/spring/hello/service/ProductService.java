package com.spring.hello.service;

import com.spring.hello.entity.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    ProductEntity createProduct(ProductEntity productEntity);

    List<ProductEntity> findAllProducts();
}
