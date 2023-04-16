package com.example.auth_test.services;

import com.example.auth_test.repos.RefreshTokenRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class RefreshTokenService implements TokenService {
    @Value("${token.refresh.secret}")
    private String REFRESH_TOKEN_SECRET;
    private final RefreshTokenRepository refreshTokenRepository;
    @Override
    public String generateToken(UserDetails userDetails) {
        return null;
    }

    @Override
    public String generateToken(UserDetails userDetails, Map<String, ?> extraClaims) {
        return null;
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        return false;
    }

    @Override
    public String extractEmail(String token) {
        return null;
    }

    @Override
    public <T> T extractSingleClaim(String token, Function<Claims, T> resolver) {
        return null;
    }

    @Override
    public Claims extractAllClaims(String token) {
        return null;
    }
}
