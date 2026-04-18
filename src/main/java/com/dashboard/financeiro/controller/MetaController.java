package com.dashboard.financeiro.controller;

import com.dashboard.financeiro.model.Meta;
import com.dashboard.financeiro.service.MetaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.*;

import java.util.List;

@RestController
@Tag(name = "Metas", description = "Controle de metas financeiras")
@RequestMapping("/metas")
@CrossOrigin("*")
@AllArgsConstructor
public class MetaController {
    private final MetaService metaService;

    @PostMapping
    public Meta salvar(@RequestBody Meta meta){
        return metaService.salvar(meta);
    }
    @GetMapping
    public List<Meta> listar(@RequestParam int mes, @RequestParam int ano){
        return metaService.listar(mes, ano);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMeta(@PathVariable Long id){
        metaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
