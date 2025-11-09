package com.vijaya.user.utils;


import com.vijaya.user.Models.User;
import com.vijaya.user.dto.CustomUserDetails;
import com.vijaya.user.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {


    @Autowired
    private UserRepository userRepository;

    @Value("${jwt.secret}")
    private String secretKey;

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
    public String generateToken(String email){
        User user = userRepository.findByEmail(email);
        return Jwts.builder()
                .setSubject(email)
                .claim("userId",user.getUserId())
                .claim("role",user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000)) // 1 hour expiry
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public String extractEmail(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public Long extractUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
        return user.userId();
    }
    public Long extractUserIdFromToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token.replace("Bearer ", "")) // handle "Bearer "
                    .getBody();
            return claims.get("userId", Long.class);
        } catch (Exception e) {
            throw new RuntimeException("Invalid or expired JWT token", e);
        }
    }




    public boolean isTokenValid(String token, UserDetails user) {
        return extractEmail(token).equals(user.getUsername());
    }

}
