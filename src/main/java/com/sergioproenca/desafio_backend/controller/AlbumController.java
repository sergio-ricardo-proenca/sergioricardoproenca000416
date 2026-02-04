package com.sergioproenca.desafio_backend.controller;

import com.sergioproenca.desafio_backend.model.Album;
import com.sergioproenca.desafio_backend.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/albuns") // Adicionado /v1 para atender ao requisito (j) de versionamento
public class AlbumController {

    @Autowired
    private AlbumRepository repository;

    @GetMapping
    public Page<Album> listarTodos(@PageableDefault(size = 10) Pageable paginacao) {
        return repository.findAll(paginacao);
    }

    @PostMapping
    public Album cadastrar(@RequestBody Album album) {
        return repository.save(album);
    }
}