package com.example.auth_test.services;

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

@Service
public class AccessTokenService implements TokenService {
    @Value("${token.access.secret}")
    private String ACCESS_TOKEN_SECRET;
    @Value("${token.access.expiryMs}")
    private Long EXPIRY_ACCESS_TOKEN_MILLIS;

    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(userDetails, new HashMap<>());
    }

    @Override
    public String generateToken(UserDetails userDetails, Map<String, ?> extraClaims) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRY_ACCESS_TOKEN_MILLIS))
                .signWith(getSingingKey(ACCESS_TOKEN_SECRET), SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        return !isTokenExpired(token) && extractEmail(token).equals(userDetails.getUsername());
    }

    @Override
    public String extractEmail(String token) {
        return extractSingleClaim(token, Claims::getSubject);
    }

    @Override
    public <T> T extractSingleClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    @Override
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSingingKey(ACCESS_TOKEN_SECRET))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractSingleClaim(token, Claims::getExpiration)
                .before(new Date(System.currentTimeMillis()));
    }

    private Key getSingingKey(String secret) {
        byte[] key = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(key);
    }

}
