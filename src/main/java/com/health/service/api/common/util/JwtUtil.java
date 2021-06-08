package com.health.service.api.common.util;

public interface JwtUtil {
    String createToken();

    void verifyToken(String token);
}
