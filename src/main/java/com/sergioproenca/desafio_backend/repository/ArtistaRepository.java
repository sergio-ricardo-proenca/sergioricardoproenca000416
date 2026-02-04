package com.sergioproenca.desafio_backend.repository;

import com.sergioproenca.desafio_backend.model.Artista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    // Requisito (f): Busca por nome ignorando maiúsculas/minúsculas
    Page<Artista> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}