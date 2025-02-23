package org.example.backend.security.security_services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.accessTokenValidityMS}")
    private int jwtExpirationMs;

    public String createToken(String email, boolean isPremium) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

        return Jwts.builder()
                .header()
                .and()
                .subject(email)
                .claim("roles", isPremium ? "ROLE_PREMIUM" : "ROLE_FREE")
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key)
                .compact();
    }

    public Claims validateToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

        JwtParser parser = Jwts.parser()
                .verifyWith(key)
                .build();

        try {
            return (Claims) parser.parse(token.substring(token.indexOf(" ") + 1)).getPayload();
        } catch (Exception e) {
            throw new UsernameNotFoundException("Invalid token");
        }
    }
}
