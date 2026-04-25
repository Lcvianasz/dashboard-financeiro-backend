package com.dashboard.financeiro.repository;

import com.dashboard.financeiro.model.Ativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AtivoRepository extends  JpaRepository<Ativo, Long>{
    Optional<Ativo> findBySimbolo(String simbolo);
    boolean existsBySimbolo(String simbolo);
}
