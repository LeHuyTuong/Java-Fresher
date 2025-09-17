package com.tuonglh.coffee.samplecode.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class TokenResponse implements Serializable {
    private String accessToken; // key truy cap he thong
    private String refreshToken; // lam moi token
    private Long userId;
    //more over
}
