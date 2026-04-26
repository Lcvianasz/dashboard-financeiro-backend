package com.dashboard.financeiro.controller;

import com.dashboard.financeiro.dto.AlertaDTO;
import com.dashboard.financeiro.dto.CategoriaDTO;
import com.dashboard.financeiro.dto.DashboardDTO;
import com.dashboard.financeiro.service.DashboardService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin("*")
@AllArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping
    public DashboardDTO resumo(@RequestParam int mes, @RequestParam int ano){
        return dashboardService.getResumoMensal(mes, ano);
    }
    @GetMapping("/categorias")
    public List<CategoriaDTO> categorias(@RequestParam int mes, @RequestParam int ano){
        return dashboardService.getGastosPorCategoria(mes, ano);
    }
    @GetMapping("/alertas")
    public List<AlertaDTO> alertas(
            @RequestParam int mes,
            @RequestParam int ano
    ) {
        return dashboardService.verificarAlertas(mes, ano);
    }
}
