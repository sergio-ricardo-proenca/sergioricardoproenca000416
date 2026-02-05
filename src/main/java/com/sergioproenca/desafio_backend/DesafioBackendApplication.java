package com.sergioproenca.desafio_backend;

import com.sergioproenca.desafio_backend.service.RegionalSyncService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DesafioBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioBackendApplication.class, args);
    }

    // Este bloco faz a sincronização rodar toda vez que o projeto inicia
    @Bean
    CommandLineRunner runSync(RegionalSyncService syncService) {
        return args -> {
            System.out.println("🚀 Iniciando sincronização de regionais...");
            syncService.sincronizar();
        };
    }
}