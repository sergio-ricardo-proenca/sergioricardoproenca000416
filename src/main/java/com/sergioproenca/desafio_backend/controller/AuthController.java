package com.sergioproenca.desafio_backend.controller;

import com.sergioproenca.desafio_backend.config.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public String login() {
        // Requisito (b) do Edital: Gerar token com validade de 5 minutos
        return tokenService.gerarToken();
    }
}