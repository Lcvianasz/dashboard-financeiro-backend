package com.dashboard.financeiro.repository;

import com.dashboard.financeiro.model.TipoTransacao;
import com.dashboard.financeiro.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findByDataBetween(LocalDate inicio, LocalDate fim);

    @Query("SELECT SUM(t.valor) FROM Transacao t WHERE t.tipo = :tipo AND t.data BETWEEN :inicio AND :fim")
    Double somarPorTipo(TipoTransacao tipo, LocalDate inicio, LocalDate fim);

    @Query("""
        SELECT t.categoria, SUM(t.valor)
        FROM Transacao t
        WHERE t.tipo = 'SAIDA' AND t.data BETWEEN :inicio AND :fim
        GROUP BY t.categoria
    """)
    List<Object[]> gastosPorCategoria(LocalDate inicio, LocalDate fim);
}
