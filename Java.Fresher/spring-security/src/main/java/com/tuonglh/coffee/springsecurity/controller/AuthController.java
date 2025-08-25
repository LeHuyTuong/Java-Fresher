package com.tuonglh.coffee.springsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @GetMapping("/login")
    public ResponseEntity<String> login(){
        return ResponseEntity.ok("login success");
    }

    @GetMapping("/register")
    public ResponseEntity<String> register(){
        return ResponseEntity.ok("register success");
    }
}
