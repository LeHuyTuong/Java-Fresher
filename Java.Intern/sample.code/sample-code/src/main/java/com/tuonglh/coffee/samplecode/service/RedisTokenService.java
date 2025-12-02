package com.tuonglh.coffee.samplecode.service;

import com.tuonglh.coffee.samplecode.exception.ResourceNotFoundException;
import com.tuonglh.coffee.samplecode.model.RedisToken;
import com.tuonglh.coffee.samplecode.repository.RedisTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisTokenService {

    private final RedisTokenRepository redisTokenRepository;

    public String save(RedisToken token){
        RedisToken result = redisTokenRepository.save(token);
        return result.getId();
    }

    public void delete(String id){
        redisTokenRepository.deleteById(id);
    }

    public RedisToken getById(String id){
        return redisTokenRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Token not found"));
    }
}
