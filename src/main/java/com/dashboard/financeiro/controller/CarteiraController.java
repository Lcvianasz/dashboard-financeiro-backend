package com.dashboard.financeiro.controller;

import com.dashboard.financeiro.dto.CarteiraResponseDTO;
import com.dashboard.financeiro.service.CarteiraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carteira")
@RequiredArgsConstructor
@Tag(name = "Carteira", description = "Endpoints para gerenciar a carteira de investimentos")
public class CarteiraController {

    private final CarteiraService carteiraService;

    private Long getUsuarioId() {
        return 1L;
    }

    @PostMapping
    @Operation(summary = "Criar uma nova carteira para o usuário")
    public ResponseEntity<CarteiraResponseDTO> criarCarteira(@RequestParam String nome, @RequestParam(required = false) String descricao) {
        CarteiraResponseDTO response = carteiraService.criarCarteira(getUsuarioId(), nome, descricao);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
