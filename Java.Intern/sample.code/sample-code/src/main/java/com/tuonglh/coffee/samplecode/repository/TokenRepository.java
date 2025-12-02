package com.tuonglh.coffee.samplecode.repository;

import com.tuonglh.coffee.samplecode.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, String> {
    Optional<Token> findByUsername(String username);
}
