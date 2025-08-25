package com.tuonglh.coffee.springsecurity.controller;


import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/admin")
@RolesAllowed("ADMIN")
//@PostAuthorize("hasIpAddress("1122.222")")
public class AdminController {

    @RolesAllowed("ADMIN")
    @GetMapping("/vip")
    public String zoneVip(){
        return  "zoneVip";
    }
    @RolesAllowed({"USER", "ADMIN"})
    @GetMapping("/normal")
    public String zoneNorMal(){
        return "zoneNorMal";
    }

    @GetMapping("/info")
    public Authentication getInfo(){
        return SecurityContextHolder.getContext().getAuthentication();
    }


}
