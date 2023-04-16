package com.example.auth_test.services;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Map;
import java.util.function.Function;

@Service
public interface TokenService {
    String generateToken(UserDetails userDetails);
    String generateToken(UserDetails userDetails, Map<String, ?> extraClaims);
    boolean isTokenValid(String token, UserDetails userDetails);
    String extractEmail(String token);
    <T> T extractSingleClaim(String token, Function<Claims, T> resolver);
    Claims extractAllClaims(String token);
}

