package com.kramti.service;

import com.kramti.entity.User;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JwtService {
    public String generateToken(User user){
        return Jwt.issuer("https://kramti.com")
                .upn(user.getUsername())
                .groups(user.getRole())
                .expiresAt(System.currentTimeMillis() + (60 * 60 * 8)) // valid for 8 hours
                .sign();
    }
}
