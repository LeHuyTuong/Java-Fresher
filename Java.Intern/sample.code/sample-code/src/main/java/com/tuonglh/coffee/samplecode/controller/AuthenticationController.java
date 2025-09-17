package com.tuonglh.coffee.samplecode.controller;


import com.tuonglh.coffee.samplecode.dto.request.SignInRequest;
import com.tuonglh.coffee.samplecode.dto.response.TokenResponse;
import com.tuonglh.coffee.samplecode.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Validated
@Slf4j
@Tag(name = "Authentication Controller", description = "API for authentication and authorization")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/access") // đăng nhập
    public ResponseEntity<TokenResponse> login(@RequestBody SignInRequest  signInRequest) {
        return new ResponseEntity<>(authenticationService.authenticate(signInRequest), HttpStatus.OK);
    }

    @PostMapping("/refresh") // làm mới token
    public String refresh() {
        return "successful refresh token";
    }

    @PostMapping("/logout") // đăng xuất
    public String logout() {
        return "Logout successful";
    }
    //quên email , giử email ko cần token , thiết lập vào whitelist

}
