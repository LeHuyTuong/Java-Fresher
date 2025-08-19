package com.spring.hello.repository;

import com.spring.hello.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepositoty extends JpaRepository<ProductEntity, Long> {

}
