package com.spring.hello.entity;

import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Table(name = "java_product_001")
public class ProductEntity {
    private Long productId;
    private String productName;
    private BigDecimal productPrice;
}
