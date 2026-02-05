package com.sergioproenca.desafio_backend.service;

import com.sergioproenca.desafio_backend.model.Album;
import com.sergioproenca.desafio_backend.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile; // Adicionado
import java.util.List;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository repository;

    @Autowired
    private CapaService capaService; // Adicionado para o MinIO

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public List<Album> listarTodos() {
        return repository.findAll();
    }

    // Método Robusto para o Controller usar
    public Album salvarCompleto(String titulo, MultipartFile capa) throws Exception {
        // 1. Envia a foto para o MinIO (Requisito h)
        String nomeFoto = capaService.enviarCapa(capa);

        // 2. Cria e salva o objeto Álbum no banco
        Album album = new Album();
        album.setTitulo(titulo);
        album.setCapaUrl(nomeFoto);
        
        Album salvo = repository.save(album);

        // 3. Requisito Sênior (c): Notifica via WebSocket
        messagingTemplate.convertAndSend("/topic/albuns", "Novo álbum cadastrado: " + salvo.getTitulo());

        return salvo;
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}