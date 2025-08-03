package com.spring.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class Hello {
    @GetMapping("v1/api/hello")
    public String Status(){
        return "Hello , Tuong ngu";
    }
}
