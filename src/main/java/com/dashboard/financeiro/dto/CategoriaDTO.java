package com.dashboard.financeiro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CategoriaDTO {
    private String categoria;
    private Double total;
    private Double porcentagem;
}
