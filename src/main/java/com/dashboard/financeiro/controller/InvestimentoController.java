package com.dashboard.financeiro.controller;

import com.dashboard.financeiro.dto.CarteiraResponseDTO;
import com.dashboard.financeiro.dto.CompraVendaRequestDTO;
import com.dashboard.financeiro.service.InvestimentoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/investimentos")
@RequiredArgsConstructor
public class InvestimentoController {

    private final InvestimentoService investimentoService;

    @GetMapping("/carteira")
    @Operation(summary = "Obter carteira com cotações atualizadas")
    public ResponseEntity<CarteiraResponseDTO> obterCarteira() {
        Long usuarioId = 1L; // ID fixo enquanto o frontend não envia header
        return ResponseEntity.ok(investimentoService.obterCarteiraComCotacoes(usuarioId));
    }

    @PostMapping("/comprar")
    @Operation(summary = "Registrar compra de ativo")
    public ResponseEntity<String> comprar(@RequestBody CompraVendaRequestDTO request,
                                          @RequestHeader(value = "X-User-Id", required = false) Long usuarioId) {
        if (usuarioId == null) usuarioId = 1L; // fallback
        investimentoService.comprar(usuarioId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Compra registrada com sucesso");
    }

    @PostMapping("/vender")
    @Operation(summary = "Registrar venda de ativo")
    public ResponseEntity<String> vender(@RequestBody CompraVendaRequestDTO request,
                                         @RequestHeader(value = "X-User-Id", required = false) Long usuarioId) {
        if (usuarioId == null) usuarioId = 1L;
        investimentoService.vender(usuarioId, request);
        return ResponseEntity.ok("Venda registrada com sucesso");
    }
}