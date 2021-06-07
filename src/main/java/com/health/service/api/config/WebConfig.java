package com.health.service.api.config;

import com.health.service.api.config.interceptor.JwtAuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final String[] INTERCEPTOR_WHITE_LIST = {
            "/users/sign-in/**",
            "/users/login/**"
    };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtAuthInterceptor())
                .addPathPatterns("/*")
                .excludePathPatterns(INTERCEPTOR_WHITE_LIST);
    }
}
