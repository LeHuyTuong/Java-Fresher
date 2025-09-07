package com.tuonglh.coffee.samplecode.repository;

import com.tuonglh.coffee.samplecode.model.RedisToken;
import com.tuonglh.coffee.samplecode.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RedisTokenRepository extends JpaRepository<RedisToken, String> {
    Optional<RedisToken> findByUsername(String token);
}
