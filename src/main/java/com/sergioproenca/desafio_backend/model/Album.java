package com.sergioproenca.desafio_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "albuns")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    private String capaUrl; // Preparando para o requisito do MinIO (S3)

    @ManyToMany(mappedBy = "albuns")
    @JsonIgnoreProperties("albuns")
    private Set<Artista> artistas = new HashSet<>();

    // Construtores
    public Album() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getCapaUrl() { return capaUrl; }
    public void setCapaUrl(String capaUrl) { this.capaUrl = capaUrl; }
    public Set<Artista> getArtistas() { return artistas; }
    public void setArtistas(Set<Artista> artistas) { this.artistas = artistas; }
}