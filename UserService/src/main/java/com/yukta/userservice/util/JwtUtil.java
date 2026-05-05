package com.yukta.userservice.util;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET = "mySecretKeyMySecretKeyMySecretKey12345";
    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String userName) {
        return Jwts.builder().setSubject(userName).setIssuedAt(new Date()).setExpiration(
                new Date(System.currentTimeMillis()+1000 *60*60))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
