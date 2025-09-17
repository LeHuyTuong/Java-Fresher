package com.tuonglh.coffee.samplecode.repository;

import com.tuonglh.coffee.samplecode.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // persistence layer
public interface UserRepository extends JpaRepository<User, Long> {
        Optional<User> findByUsername(String username); // find user by username
        Optional<User> findByEmail(String email); // Optional để tránh lỗi null pointer exception if else cho dễ

}
