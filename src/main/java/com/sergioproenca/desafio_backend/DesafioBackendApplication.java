package com.sergioproenca.desafio_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.sergioproenca.desafio_backend") // Força o escaneamento total
public class DesafioBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(DesafioBackendApplication.class, args);
    }
}