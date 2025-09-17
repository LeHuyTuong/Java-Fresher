package com.tuonglh.coffee.samplecode.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {

    public String generateToken(UserDetails userDetails);

}
