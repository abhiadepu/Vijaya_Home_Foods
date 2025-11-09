package com.vijaya.jwt_auth.security;



import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String SECRET;

    public Long extractUserId(String token) {
        return Long.valueOf(getClaims(token).getSubject());
    }

    public boolean validateToken(String token) {
        try { getClaims(token); return true; }
        catch (Exception e) { return false; }
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody();
    }
}
