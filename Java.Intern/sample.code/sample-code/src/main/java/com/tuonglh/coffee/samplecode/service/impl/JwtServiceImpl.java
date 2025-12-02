package com.tuonglh.coffee.samplecode.service.impl;

import com.tuonglh.coffee.samplecode.exception.InvalidDataException;
import com.tuonglh.coffee.samplecode.service.JWTService;
import com.tuonglh.coffee.samplecode.util.TokenType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static com.tuonglh.coffee.samplecode.util.TokenType.*;

@Service
public class JwtServiceImpl implements JWTService {

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.expiryTime}")
    private long expiryHour;

    @Value("${jwt.refreshKey}")
    private String refreshKey;

    @Value("${jwt.resetKey}")
    private String resetKey;

    @Override
    public String extractUserName(String token,  TokenType type) {
        return extractClaim(token, Claims::getSubject, type);
    }

    @Override
    public String generateRefreshToken(UserDetails userDetails) {
        return generateRefreshToken(new HashMap<>(), userDetails);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    @Override
    public String generateResetToken(UserDetails userDetails) {
        return generateResetToken(new HashMap<>(),userDetails);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails, TokenType type) {
        final String userName = extractUserName(token,type);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token, type);
    }

    // kiem tra tg hop le
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers, TokenType tokenType) {
        final Claims claims = extractAllClaims(token, tokenType);
        return claimsResolvers.apply(claims);
    }

    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSigningKey(ACCESS_TOKEN), SignatureAlgorithm.HS256)
                .compact();
    }

    private String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24 * expiryHour))
                .signWith(getSigningKey(REFRESH_TOKEN), SignatureAlgorithm.HS256)
                .compact();
    }

    private String generateResetToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSigningKey(RESET_TOKEN), SignatureAlgorithm.HS256).compact();
    }

    private Key getKey(TokenType type){
        switch (type){
            case ACCESS_TOKEN -> {return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));}
            case REFRESH_TOKEN -> {return Keys.hmacShaKeyFor(Decoders.BASE64.decode(refreshKey));}
            case RESET_TOKEN -> {return Keys.hmacShaKeyFor(Decoders.BASE64.decode(resetKey));}
            default -> throw new InvalidDataException("Token type invalid");
        }
    }

    private boolean isTokenExpired(String token, TokenType type) {
        return extractExpiration(token,type).before(new Date());
    }

    private Date extractExpiration(String token, TokenType type) {
        return extractClaim(token, Claims::getExpiration, type);
    }

    private Claims extractAllClaims(String token, TokenType type) {
        return Jwts.parser()
                .setSigningKey(getSigningKey(type))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey(TokenType type) {
        byte[] keyBytes;
        if(ACCESS_TOKEN.equals(type)){
            keyBytes = Decoders.BASE64.decode(secretKey);
        }else{
            keyBytes = Decoders.BASE64.decode(refreshKey);
        }
        return Keys.hmacShaKeyFor(keyBytes);
    }
}