package com.sergioproenca.desafio_backend.service;

import com.sergioproenca.desafio_backend.model.Regional;
import com.sergioproenca.desafio_backend.repository.RegionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class RegionalSyncService {

    @Autowired
    private RegionalRepository repository;

    private final RestTemplate restTemplate = new RestTemplate();

    @Transactional
    public void sincronizar() {
        String url = "https://integrador-argus-api.geia.vip/v1/regionais";
        
        try {
            // 1. Busca os dados (Usando Regional direto ou um DTO se preferir)
            Regional[] regionaisDaApi = restTemplate.getForObject(url, Regional[].class);
            if (regionaisDaApi == null) return;
            
            List<Regional> listaApi = Arrays.asList(regionaisDaApi);
            List<Integer> idsNaApi = listaApi.stream().map(Regional::getId).toList();

            // 2. Regra iii-2: Ausente no endpoint -> inativar
            repository.inativarAusentes(idsNaApi);

            // 3. Processa cada regional da API
            for (Regional dto : listaApi) {
                Optional<Regional> atualOpt = repository.findByIdAndAtivoTrue(dto.getId());

                if (atualOpt.isEmpty()) {
                    // Regra iii-1: Novo -> inserir
                    salvarNovo(dto.getId(), dto.getNome());
                } else {
                    Regional atual = atualOpt.get();
                    // Regra iii-3: Atributo alterado -> inativar antigo e criar novo
                    if (!atual.getNome().equals(dto.getNome())) {
                        atual.setAtivo(false);
                        repository.save(atual);
                        salvarNovo(dto.getId(), dto.getNome());
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Erro na sincronização: " + e.getMessage());
        }
    }

    private void salvarNovo(Integer id, String nome) {
        Regional novo = new Regional();
        novo.setId(id);
        novo.setNome(nome);
        novo.setAtivo(true);
        repository.save(novo);
    }
}