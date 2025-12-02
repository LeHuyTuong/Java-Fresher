package com.tuonglh.coffee.samplecode.dto.request;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class ResetPasswordDTO implements Serializable {
    private String secretKey;
    private String password;
    private String confirmPassword;
}
