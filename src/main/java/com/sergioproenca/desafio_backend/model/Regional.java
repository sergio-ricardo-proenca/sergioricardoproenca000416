package com.sergioproenca.desafio_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "regionais")
@Data
public class Regional {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long surrogateKey; // Chave primária do banco para permitir histórico

    private Integer id; // ID que vem da API externa
    private String nome;
    private Boolean ativo;
}