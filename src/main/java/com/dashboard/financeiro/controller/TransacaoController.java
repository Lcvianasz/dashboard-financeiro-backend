package com.dashboard.financeiro.controller;

import com.dashboard.financeiro.dto.TransacaoRequestDTO;
import com.dashboard.financeiro.dto.TransacaoResponseDTO;
import com.dashboard.financeiro.model.Transacao;
import com.dashboard.financeiro.service.TrasacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Tag(name = "Transações", description = "Operações de entradas e saídas financeiras")
@RestController
@RequestMapping("/transacoes")
@CrossOrigin("*")
public class TransacaoController {

    private final TrasacaoService trasacaoService;

    @Operation(summary = "Criar uma nova transação")
    @PostMapping
    public TransacaoResponseDTO salvar(@RequestBody @Valid TransacaoRequestDTO dto){
        return trasacaoService.toDTO(trasacaoService.salvar(dto));
    }

    @Operation(summary = "Listar todas as transações")
    @GetMapping
    public List<TransacaoResponseDTO> listar(){
        return trasacaoService.listar();
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        trasacaoService.deletar(id);
    }
}
