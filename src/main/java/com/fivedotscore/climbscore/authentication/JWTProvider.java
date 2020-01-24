package com.fivedotscore.climbscore.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.Key;

@Service
public class JWTProvider {

    private String key;

    @PostConstruct
    public void init() {
        this.key = System.getenv("JWT_SECRET");

    }

    public String generateToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .signWith(SignatureAlgorithm.HS512,this.key)
                .compact();
    }

    public boolean validateToken(String jwt) {
        Jwts.parser().setSigningKey(this.key).parseClaimsJws(jwt);
        return true;
    }

    public String getUsernameFromJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(this.key)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
