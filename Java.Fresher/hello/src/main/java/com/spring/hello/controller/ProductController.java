package com.spring.hello.controller;


import com.spring.hello.entity.ProductEntity;
import com.spring.hello.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
//localhost:8080/v1/api
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping("/product/add")
    public ProductEntity createProduct(ProductEntity productEntity) {
        return productService.createProduct(productEntity);
    }

    /**
     *List Alll
     * @return List
     */

    @GetMapping("/products")
    public List<ProductEntity> getAllProducts(){
        return productService.findAllProducts();
    }
}
