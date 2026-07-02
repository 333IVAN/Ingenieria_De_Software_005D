package com.kiwi.service_auth.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(String rut, List<String> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles); 
        return createToken(claims, rut);
    }

    private String createToken(Map<String, Object> claims, String rut) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(rut) 
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2)) 
                .signWith(getSignKey(), SignatureAlgorithm.HS256) 
                .compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}