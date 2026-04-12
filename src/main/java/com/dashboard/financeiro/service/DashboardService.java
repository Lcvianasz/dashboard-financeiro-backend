package com.dashboard.financeiro.service;

import com.dashboard.financeiro.dto.AlertaDTO;
import com.dashboard.financeiro.dto.CategoriaDTO;
import com.dashboard.financeiro.dto.DashboardDTO;
import com.dashboard.financeiro.model.Meta;
import com.dashboard.financeiro.model.TipoTransacao;
import com.dashboard.financeiro.repository.MetaRepository;
import com.dashboard.financeiro.repository.TransacaoRepository;
import org.springframework.stereotype.Service;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class DashboardService {

    private final TransacaoRepository repository;
    private final MetaRepository metaRepository;


    public DashboardDTO getResumoMensal(int mes, int ano) {

        LocalDate inicio = LocalDate.of(ano, mes, 1);
        LocalDate fim = inicio.withDayOfMonth(inicio.lengthOfMonth());

        Double entradas = repository.somarPorTipo(TipoTransacao.ENTRADA, inicio, fim);
        Double saidas = repository.somarPorTipo(TipoTransacao.SAIDA, inicio, fim);

        entradas = (entradas != null) ? entradas : 0.0;
        saidas = (saidas != null) ? saidas : 0.0;

        Double saldo = entradas - saidas;

        return new DashboardDTO(entradas, saidas, saldo);
    }

    public List<CategoriaDTO> getGastosPorCategoria(int mes, int ano) {

        LocalDate inicio = LocalDate.of(ano, mes, 1);
        LocalDate fim = inicio.withDayOfMonth(inicio.lengthOfMonth());

        List<Object[]> resultados = repository.gastosPorCategoria(inicio, fim);

        double totalGeral = resultados.stream()
                .mapToDouble(r -> (Double) r[1])
                .sum();

        List<CategoriaDTO> lista = new ArrayList<>();

        for (Object[] r : resultados) {
            String categoria = (String) r[0];
            Double total = (Double) r[1];

            double porcentagem = totalGeral > 0
                    ? (total / totalGeral) * 100
                    : 0;

            lista.add(new CategoriaDTO(categoria, total, porcentagem));
        }

        return lista;
    }

    public List<AlertaDTO> verificarAlertas(int mes, int ano) {

        LocalDate inicio = LocalDate.of(ano, mes, 1);
        LocalDate fim = inicio.withDayOfMonth(inicio.lengthOfMonth());

        List<Object[]> gastos = repository.gastosPorCategoria(inicio, fim);
        List<Meta> metas = metaRepository.findByMesAndAno(mes, ano);

        List<AlertaDTO> alertas = new ArrayList<>();

        for (Meta meta : metas) {

            double gasto = gastos.stream()
                    .filter(g -> g[0].equals(meta.getCategoria()))
                    .map(g -> (Double) g[1])
                    .findFirst()
                    .orElse(0.0);

            boolean excedeu = gasto > meta.getValorMeta();

            alertas.add(new AlertaDTO(
                    meta.getCategoria(),
                    gasto,
                    meta.getValorMeta(),
                    excedeu
            ));
        }

        return alertas;
    }
}