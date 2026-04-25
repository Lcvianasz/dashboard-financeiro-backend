package com.dashboard.financeiro.service;

import com.dashboard.financeiro.dto.CotacaoDTO;
import com.dashboard.financeiro.dto.MapeamentoRespostaApiBrapi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class BrapiService {

    private final WebClient brapiWebClient;

    public Mono<CotacaoDTO> buscarCotacao(String simbolo) {
        return brapiWebClient.get()
                .uri("/quote/{simbolo}?modules=summaryProfile", simbolo)
                .retrieve()
                .bodyToMono(MapeamentoRespostaApiBrapi.class)
                .map(resposta -> {
                    if (resposta.getResults() == null || resposta.getResults().isEmpty()) {
                        return null;
                    }
                    var stock = resposta.getResults().get(0);
                    return CotacaoDTO.builder()
                            .simbolo(stock.getSymbol())
                            .preco(BigDecimal.valueOf(stock.getRegularMarketPrice()))
                            .variacaoPercentual(BigDecimal.valueOf(stock.getRegularMarketChangePercent()))
                            .nomeEmpresa(stock.getLongName())
                            .build();
                })
                .onErrorResume(e -> {
                    log.error("Erro ao buscar cotação de {}: {}", simbolo, e.getMessage());
                    return Mono.empty();
                });
    }
}