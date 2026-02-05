package com.sergioproenca.desafio_backend.service;

import com.sergioproenca.desafio_backend.model.Album;
import com.sergioproenca.desafio_backend.repository.AlbumRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AlbumServiceTest {

    @Mock
    private AlbumRepository repository; // Ajustado: O nome no Service é 'repository'

    @Mock
    private CapaService capaService;

    @Mock
    private SimpMessagingTemplate messagingTemplate;

    @InjectMocks
    private AlbumService albumService;

    @Test
    @DisplayName("Deve salvar um álbum completo, enviar para o S3 e notificar via WebSocket")
    void deveSalvarAlbumComSucesso() throws Exception {
        // GIVEN (Preparação)
        String tituloTeste = "Álbum de Teste";
        MultipartFile arquivoMock = mock(MultipartFile.class);
        
        Album albumSalvo = new Album();
        albumSalvo.setTitulo(tituloTeste);
        albumSalvo.setCapaUrl("foto-teste.jpg");

        // Configurando os comportamentos simulados (Mocks)
        when(capaService.enviarCapa(any())).thenReturn("foto-teste.jpg");
        when(repository.save(any(Album.class))).thenReturn(albumSalvo);

        // WHEN (Execução)
        // Usando o seu método real: salvarCompleto(String, MultipartFile)
        Album resultado = albumService.salvarCompleto(tituloTeste, arquivoMock);

        // THEN (Validação)
        assertNotNull(resultado);
        assertEquals(tituloTeste, resultado.getTitulo());
        
        // Verifica se os métodos importantes foram chamados
        verify(capaService, times(1)).enviarCapa(any());
        verify(repository, times(1)).save(any());
        verify(messagingTemplate, times(1)).convertAndSend(eq("/topic/albuns"), anyString());
    }
}