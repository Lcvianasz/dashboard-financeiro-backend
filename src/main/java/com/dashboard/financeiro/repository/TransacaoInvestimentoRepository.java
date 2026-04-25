package com.dashboard.financeiro.repository;

import com.dashboard.financeiro.model.TransacaoInvestimento;
import com.dashboard.financeiro.model.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoInvestimentoRepository extends JpaRepository<TransacaoInvestimento, Long> {
    List<TransacaoInvestimento> findByCarteiraOrderByDataTransacaoDesc(Carteira carteira);
}
