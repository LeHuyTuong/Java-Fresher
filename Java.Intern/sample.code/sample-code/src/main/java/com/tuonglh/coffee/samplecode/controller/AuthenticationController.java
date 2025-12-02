package com.tuonglh.coffee.samplecode.controller;


import com.tuonglh.coffee.samplecode.dto.request.ResetPasswordDTO;
import com.tuonglh.coffee.samplecode.dto.request.SignInRequest;
import com.tuonglh.coffee.samplecode.dto.request.UserRequestDTO;
import com.tuonglh.coffee.samplecode.dto.response.ResponseData;
import com.tuonglh.coffee.samplecode.dto.response.TokenResponse;
import com.tuonglh.coffee.samplecode.service.AuthenticationService;
import com.tuonglh.coffee.samplecode.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Validated
@Slf4j
@Tag(name = "Authentication Controller", description = "API for authentication and authorization")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/register") // API Đăng ký công khai
    public ResponseEntity<ResponseData<Long>> register(@RequestBody @Valid UserRequestDTO request) {
        // Gọi service lưu user như cũ
        long userId = 0;
        try {
            userId = userService.saveUser(request);
        } catch (Exception e) {
            // Xử lý lỗi
            return new ResponseEntity<>(new ResponseData<>(HttpStatus.BAD_REQUEST.value(), "Register failed"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseData<>(HttpStatus.CREATED.value(), "Register success", userId), HttpStatus.CREATED);
    }
    @PostMapping("/access-token") // đăng nhập
    public ResponseEntity<TokenResponse> login(@RequestBody SignInRequest  signInRequest) {
        return new ResponseEntity<>(authenticationService.accessToken(signInRequest), HttpStatus.OK);
    }

    @PostMapping("/refresh-token") // làm mới token
    public ResponseEntity<TokenResponse> refresh(HttpServletRequest request) {
        return new ResponseEntity<>(authenticationService.refresh(request), HttpStatus.OK);
    }

    @PostMapping("/remove-token") // đăng xuất
    public ResponseEntity<String> logout(HttpServletRequest request) {
        return new ResponseEntity<>(authenticationService.logout(request), HttpStatus.OK);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody String email) {
        return new ResponseEntity<>(authenticationService.forgotPassword(email), HttpStatus.OK);
    }
    //quên email , giử email ko cần token , thiết lập vào whitelist

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody String secretKey) {
        return new ResponseEntity<>(authenticationService.resetPassword(secretKey), HttpStatus.OK);
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordDTO request) {
        return new ResponseEntity<>(authenticationService.changePassword(request), HttpStatus.OK);
    }


}
