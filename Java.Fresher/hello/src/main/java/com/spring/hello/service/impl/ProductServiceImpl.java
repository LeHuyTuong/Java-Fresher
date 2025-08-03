package com.spring.hello.service.impl;

import com.spring.hello.entity.ProductEntity;
import com.spring.hello.repository.ProductRepositoty;
import com.spring.hello.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepositoty productRepositoty;

    @Override
    public ProductEntity createProduct(ProductEntity productEntity) {
        return productRepositoty.createProduct(productEntity);
    }

    @Override
    public List<ProductEntity> findAllProducts() {
        return productRepositoty.findAllProducts();
    }
}
