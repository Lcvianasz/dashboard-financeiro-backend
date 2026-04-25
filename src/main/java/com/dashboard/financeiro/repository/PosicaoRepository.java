package com.dashboard.financeiro.repository;

import com.dashboard.financeiro.model.Carteira;
import com.dashboard.financeiro.model.Posicao;
import com.dashboard.financeiro.model.Ativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PosicaoRepository extends JpaRepository<Posicao, Long> {
    Optional<Posicao> findByCarteiraAndAtivo(Carteira carteira, Ativo ativo);
    void deleteByCarteiraAndAtivo(Carteira carteira, Ativo ativo);
}
