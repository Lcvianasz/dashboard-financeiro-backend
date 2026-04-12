package com.dashboard.financeiro.service;

import com.dashboard.financeiro.dto.CategoriaDTO;
import com.dashboard.financeiro.dto.DashboardDTO;
import com.dashboard.financeiro.model.TipoTransacao;
import com.dashboard.financeiro.repository.TransacaoRepository;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@Service
public class DashboardService {
    private final TransacaoRepository transacaoRepository;

    public DashboardDTO getResumoMensal(int mes, int ano){
        LocalDate inicio = LocalDate.of(ano, mes, 1);
        LocalDate fim = inicio.withDayOfMonth(inicio.lengthOfMonth());

        Double entradas = transacaoRepository.somarPorTipo(TipoTransacao.ENTRADA, inicio, fim);
        Double saidas = transacaoRepository.somarPorTipo(TipoTransacao.SAIDA, inicio, fim);

        entradas = entradas != null ? entradas : 0.0;
        saidas = saidas != null ? saidas : 0.0;

        Double saldo = entradas - saidas;

        return new DashboardDTO(entradas, saidas, saldo);
    }

    public List<CategoriaDTO> getGastosPorCategoria(int mes, int ano){

        LocalDate inicio = LocalDate.of(ano, mes, 1);
        LocalDate fim = inicio.withDayOfMonth(inicio.lengthOfMonth());

        List<Object[]> resultados = transacaoRepository.gastosPorCategoria(inicio, fim);

        Double totalGeral = resultados.stream()
                .mapToDouble(r -> (Double) r[1])
                .sum();
        List<CategoriaDTO> lista = new ArrayList<>();

        for (Object[] r : resultados){
            String categoria = (String) r[0];
            Double total = (Double) r[1];

            double porcentagem = (total / totalGeral) * 100;
            lista.add(new CategoriaDTO(categoria, total, porcentagem));
        }
        return lista;
    }
}
