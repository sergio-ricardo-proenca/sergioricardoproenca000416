package com.sergioproenca.desafio_backend.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    private final Map<String, AtomicInteger> limitMap = new ConcurrentHashMap<>();

    public RateLimitInterceptor() {
        // Reseta o contador a cada 1 minuto
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(
            limitMap::clear, 1, 1, TimeUnit.MINUTES
        );
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String clientIp = request.getRemoteAddr();
        limitMap.putIfAbsent(clientIp, new AtomicInteger(0));
        
        if (limitMap.get(clientIp).incrementAndGet() > 10) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value()); // HTTP 429
            response.getWriter().write("Limite de requisicoes excedido. Tente novamente em 1 minuto.");
            return false;
        }
        return true;
    }
}