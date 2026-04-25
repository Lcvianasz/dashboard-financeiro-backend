package com.dashboard.financeiro.controller;

import com.dashboard.financeiro.dto.CotacaoDTO;
import com.dashboard.financeiro.service.BrapiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/cotacoes")
@RequiredArgsConstructor
public class CotacaoController {

    private final BrapiService brapiService;

    @GetMapping("/{simbolo}")
    public Mono<ResponseEntity<CotacaoDTO>> getCotacao(@PathVariable String simbolo) {
        return brapiService.buscarCotacao(simbolo)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
