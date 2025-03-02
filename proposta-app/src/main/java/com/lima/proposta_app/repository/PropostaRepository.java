package com.lima.proposta_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lima.proposta_app.entity.Proposta;

import jakarta.transaction.Transactional;

@Repository
public interface PropostaRepository extends CrudRepository<Proposta, Long> {
    List<Proposta> findAllByIntegradaFalse();

    @Transactional
    @Modifying
    @Query(value = "UPDATE proposta SET aprovada = :aprovada, observacao = :observacao WHERE id = :id", nativeQuery = true)
    void atualizarProposta(Long id, boolean aprovada, String observacao);
}
