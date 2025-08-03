package com.spring.hello.repository.impl;

import com.spring.hello.entity.ProductEntity;
import com.spring.hello.repository.ProductRepositoty;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository
public class ProductRepositoryImpl implements ProductRepositoty {
    @Override
    public ProductEntity createProduct(ProductEntity productEntity) {
        ProductEntity productEntity1 = new ProductEntity();
        productEntity1.setProductId(1L);
        productEntity1.setProductName("Tips JAVA NEW");
        productEntity1.setProductPrice(new BigDecimal("21.6"));
        return productEntity1;
    }

    @Override
    public List<ProductEntity> findAllProducts() {
        ProductEntity productEntity1 = new ProductEntity();
        productEntity1.setProductId(1L);
        productEntity1.setProductName("Tips JAVA");
        productEntity1.setProductPrice(new BigDecimal("21.6"));
        return List.of(productEntity1);
    }
}
