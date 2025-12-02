package com.tuonglh.coffee.samplecode.repository;

import com.tuonglh.coffee.samplecode.model.RedisToken;
import org.springframework.data.repository.CrudRepository;

public interface RedisTokenRepository extends CrudRepository<RedisToken, String> {
}
