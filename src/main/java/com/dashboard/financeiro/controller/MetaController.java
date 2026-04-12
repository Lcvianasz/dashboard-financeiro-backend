package com.dashboard.financeiro.controller;

import com.dashboard.financeiro.model.Meta;
import com.dashboard.financeiro.service.MetaService;
import org.springframework.web.bind.annotation.*;
import lombok.*;

import java.util.List;

@RestController
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
}
