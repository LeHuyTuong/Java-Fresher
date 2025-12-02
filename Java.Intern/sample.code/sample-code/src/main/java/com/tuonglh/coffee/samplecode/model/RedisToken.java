package com.tuonglh.coffee.samplecode.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("RedisToken")
public class RedisToken implements Serializable {
    @Id
    private String id;
    private String accessToken;
    private String refreshToken;
    private String resetToken;
}
