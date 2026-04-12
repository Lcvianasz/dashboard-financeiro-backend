package com.dashboard.financeiro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AlertaDTO {
    private String categoria;
    private Double gastos;
    private Double meta;
    private Boolean excedeu;
}
