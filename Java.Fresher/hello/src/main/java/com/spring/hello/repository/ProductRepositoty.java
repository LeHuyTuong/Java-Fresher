package com.spring.hello.repository;

import com.spring.hello.entity.ProductEntity;

import java.util.List;

public interface ProductRepositoty {
    ProductEntity createProduct(ProductEntity productEntity);

    List<ProductEntity> findAllProducts();
}
