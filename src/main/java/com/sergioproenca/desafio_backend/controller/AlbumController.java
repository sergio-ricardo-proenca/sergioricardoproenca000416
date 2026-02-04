package com.sergioproenca.desafio_backend.controller;

import com.sergioproenca.desafio_backend.model.Album;
import com.sergioproenca.desafio_backend.repository.AlbumRepository;
import com.sergioproenca.desafio_backend.service.CapaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/albuns")
public class AlbumController {

    @Autowired
    private AlbumRepository repository;

    @Autowired
    private CapaService capaService;

    @PostMapping
    public ResponseEntity<Album> salvar(
            @RequestParam("titulo") String titulo, 
            @RequestParam("artista_id") Long artistaId,
            @RequestParam("capa") MultipartFile capa) {
        
        try {
            // 1. Envia a foto para o MinIO (Requisito h)
            String nomeFoto = capaService.enviarCapa(capa);

            // 2. Cria o objeto Álbum usando os métodos corretos do seu Model (Requisito g)
            Album album = new Album();
            album.setTitulo(titulo); 
            album.setCapaUrl(nomeFoto); 

            return ResponseEntity.ok(repository.save(album));
            
        } catch (Exception e) {
            e.printStackTrace(); // Isso ajuda a ver erros no console se o upload falhar
            return ResponseEntity.internalServerError().build();
        }
    }
}