package com.dashboard.financeiro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardDTO {
    private Double totalEntradas;
    private Double totalSaidas;
    private Double saldo;

}
