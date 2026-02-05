package com.sergioproenca.desafio_backend.repository;

import com.sergioproenca.desafio_backend.model.Regional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface RegionalRepository extends JpaRepository<Regional, Long> {
    
    // Busca apenas o registro que está ativo atualmente para aquele ID da API
    Optional<Regional> findByIdAndAtivoTrue(Integer id);

    // Regra iii-2: Inativa todos que NÃO estão na lista da API
    @Modifying
    @Query("UPDATE Regional r SET r.ativo = false WHERE r.id NOT IN :ids")
    void inativarAusentes(List<Integer> ids);
}