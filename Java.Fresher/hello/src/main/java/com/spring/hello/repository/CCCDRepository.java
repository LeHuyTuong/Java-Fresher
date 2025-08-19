package com.spring.hello.repository;


import com.spring.hello.entity.user.CCCDEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CCCDRepository extends JpaRepository<CCCDEntity, Long> {
}
