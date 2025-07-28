package com.spring.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {
    @GetMapping("v1/api/hello")
    public String Status(){
        return "Hello , Tuong ngu";
    }
}
