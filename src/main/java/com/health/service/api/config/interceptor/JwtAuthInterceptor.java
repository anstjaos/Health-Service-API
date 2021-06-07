package com.health.service.api.config.interceptor;

import com.health.service.api.common.exception.TokenNotMatchException;
import com.health.service.api.common.exception.UserNotFoundException;
import com.health.service.api.common.util.JwtUtil;
import com.health.service.api.user.entity.UserEntity;
import com.health.service.api.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtAuthInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    private final String HEADER_TOKEN_KEY = "token";

    @Autowired
    public JwtAuthInterceptor(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserEntity user = userRepository.findById(Integer.parseInt(request.getHeader("userId")))
                .orElseThrow(UserNotFoundException::new);

        String givenToken = request.getHeader(HEADER_TOKEN_KEY);
        verifyToken(givenToken, user.getToken());

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private void verifyToken(String givenToken, String userToken) {
        if (!givenToken.equals(userToken)) {
            throw new TokenNotMatchException();
        }

        jwtUtil.verifyToken(givenToken);
    }
}
