package org.example.backend.security.security_services;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
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

    public String createToken(String username) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

        //TODO de extras premium din database
        return Jwts.builder()
                .header()
                .and()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key)
                .claim()
                .compact();
    }

    public String validateToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

        JwtParser parser = Jwts.parser()
                .verifyWith(key)
                .build();

        try {
            Jwt jwt = parser.parse(token.substring(token.indexOf(" ") + 1));
            return ((DefaultClaims) (jwt.getPayload())).getSubject();
        } catch (Exception e) {
            throw new UsernameNotFoundException("Invalid token");
        }
    }
}
