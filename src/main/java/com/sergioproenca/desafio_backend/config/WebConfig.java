package com.sergioproenca.desafio_backend.config;

import com.sergioproenca.desafio_backend.interceptor.RateLimitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private RateLimitInterceptor rateLimitInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Aplica o limite em todos os endpoints da API
        registry.addInterceptor(rateLimitInterceptor).addPathPatterns("/api/v1/**");
    }
}