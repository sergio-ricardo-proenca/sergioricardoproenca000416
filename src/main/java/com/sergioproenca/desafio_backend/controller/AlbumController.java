package com.sergioproenca.desafio_backend.controller;

import com.sergioproenca.desafio_backend.model.Album;
import com.sergioproenca.desafio_backend.service.AlbumService; // Importamos o Service novo
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/albuns")
public class AlbumController {

    @Autowired
    private AlbumService albumService; // Agora usamos apenas o Service

    @PostMapping
    public ResponseEntity<Album> salvar(
            @RequestParam("titulo") String titulo, 
            @RequestParam("artista_id") Long artistaId,
            @RequestParam("capa") MultipartFile capa) {
        
        try {
            // Chamamos o método salvarCompleto que criamos no AlbumService.
            // Ele vai cuidar do MinIO, do Banco e do WebSocket de uma vez só!
            Album album = albumService.salvarCompleto(titulo, capa);
            
            return ResponseEntity.ok(album);
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}