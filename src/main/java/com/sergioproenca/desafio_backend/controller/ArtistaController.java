package com.sergioproenca.desafio_backend.controller;

import com.sergioproenca.desafio_backend.model.Artista;
import com.sergioproenca.desafio_backend.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/artistas") // Requisito (j): Versionamento
public class ArtistaController {

    @Autowired
    private ArtistaRepository repository;

    @GetMapping
    public Page<Artista> listar(
            @RequestParam(required = false) String nome, 
            @PageableDefault(sort = "nome", size = 10) Pageable paginacao) {
        
        // Requisito (f): Consulta por nome com paginação e ordenação
        if (nome != null && !nome.isEmpty()) {
            return repository.findByNomeContainingIgnoreCase(nome, paginacao);
        }
        
        // Requisito (d): Listagem geral paginada
        return repository.findAll(paginacao);
    }

    @PostMapping
    public Artista cadastrar(@RequestBody Artista artista) {
        // Requisito (c): Implementar POST
        return repository.save(artista);
    }
}