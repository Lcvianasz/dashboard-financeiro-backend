package com.dashboard.financeiro.controller;

import com.dashboard.financeiro.dto.CarteiraResponseDTO;
import com.dashboard.financeiro.dto.CompraVendaRequestDTO;
import com.dashboard.financeiro.dto.CotacaoDTO;
import com.dashboard.financeiro.service.BrapiService;
import com.dashboard.financeiro.service.InvestimentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/investimentos")
@RequiredArgsConstructor
@Tag(name = "Investimentos", description = "Endpoints para carteira de ações")
public class InvestimentoController {

    private final InvestimentoService investimentoService;
    private final BrapiService brapiService;

    private Long getUsuarioId() {
        return 1L;
    }

    @PostMapping("/comprar")
    @Operation(summary = "Registrar compra de um ativo")
    public ResponseEntity<String> comprar(@Valid @RequestBody CompraVendaRequestDTO request) {
        investimentoService.comprar(getUsuarioId(), request);
        return ResponseEntity.ok("Compra registrada com sucesso");
    }

    @PostMapping("/vender")
    @Operation(summary = "Registrar venda de um ativo")
    public ResponseEntity<String> vender(@Valid @RequestBody CompraVendaRequestDTO request) {
        investimentoService.vender(getUsuarioId(), request);
        return ResponseEntity.ok("Venda registrada com sucesso");
    }

    @GetMapping("/carteira")
    @Operation(summary = "Obter carteira com cotações atualizadas")
    public ResponseEntity<CarteiraResponseDTO> obterCarteira() {
        return ResponseEntity.ok(investimentoService.obterCarteiraComCotacoes(getUsuarioId()));
    }

    @GetMapping("/cotacao/{simbolo}")
    @Operation(summary = "Buscar cotação atual de um ativo")
    public Mono<CotacaoDTO> getCotacao(@PathVariable String simbolo) {
        return brapiService.buscarCotacao(simbolo);
    }
}
