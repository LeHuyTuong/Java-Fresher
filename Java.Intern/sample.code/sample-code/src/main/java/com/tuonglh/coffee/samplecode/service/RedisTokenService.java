package com.tuonglh.coffee.samplecode.service;

import com.tuonglh.coffee.samplecode.model.RedisToken;
import com.tuonglh.coffee.samplecode.repository.RedisTokenRepository;
import org.springframework.stereotype.Service;

@Service
public class RedisTokenService {

    private RedisTokenRepository redisTokenRepository;

    public String save(RedisToken redisToken){
        RedisToken result = redisTokenRepository.save(redisToken);
        return String.valueOf(result.getId());
    }

    public void delete(String id ){
        redisTokenRepository.deleteById(id);
    }


}
