package com.spring.hello.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductEntity {
    private Long productId;
    private String productName;
    private BigDecimal productPrice;
}
