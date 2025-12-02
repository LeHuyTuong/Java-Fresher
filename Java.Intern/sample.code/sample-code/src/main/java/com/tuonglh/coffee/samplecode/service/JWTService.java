package com.tuonglh.coffee.samplecode.service;

import com.tuonglh.coffee.samplecode.util.TokenType;
import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {

    String extractUserName(String token, TokenType type);

    String generateRefreshToken(UserDetails userDetails);

    String generateToken(UserDetails userDetails);

    String generateResetToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails, TokenType type);

}
