package com.dashboard.financeiro.controller;

import com.dashboard.financeiro.model.Transacao;
import com.dashboard.financeiro.service.TrasacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    public Transacao salvar(@RequestBody Transacao t){
        return trasacaoService.salvar(t);
    }
    @Operation(summary = "Listar todas as transações")
    @GetMapping
    public List<Transacao> listar(){
        return trasacaoService.listar();
    }
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        trasacaoService.deletar(id);
    }
}
