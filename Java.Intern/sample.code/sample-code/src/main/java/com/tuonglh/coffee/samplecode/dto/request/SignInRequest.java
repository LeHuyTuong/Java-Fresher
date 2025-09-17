package com.tuonglh.coffee.samplecode.dto.request;

import com.tuonglh.coffee.samplecode.util.Platform;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SignInRequest implements Serializable {

    @NotBlank(message = "username must be not null")
    private String username; // bat buoc

    @NotBlank(message = "password must be not null")
    private String password; // bat buoc

    @NotNull(message = "flatform must be not nul ")
    private Platform platform;

    private String deviceToken; // put notify , moi cai mobi se co 1 deviceToken , trong 1 thoi diem dang nhap chi co 1 deviceToken de ghi log

    private String version; //web thi khoi , nhung ma mobi thi can
}
