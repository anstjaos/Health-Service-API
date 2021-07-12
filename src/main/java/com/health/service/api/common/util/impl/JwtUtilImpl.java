package com.health.service.api.common.util.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.health.service.api.common.util.JwtUtil;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtilImpl implements JwtUtil {
    private final String TOKEN_KEY = "HEALTH_SERVICE";
    private final String ISSUER = "anstjaos";

    @Override
    public String createToken() {
        return JWT.create()
                .withIssuer(ISSUER)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60))
                .sign(Algorithm.HMAC256(TOKEN_KEY));
    }

    @Override
    public void verifyToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_KEY))
                .withIssuer(ISSUER)
                .build();

        verifier.verify(token);
    }
}
