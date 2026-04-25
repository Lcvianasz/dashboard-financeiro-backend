package com.dashboard.financeiro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CompraVendaRequestDTO {

    @NotBlank
    private String simbolo;

    @NotNull @Positive
    private BigDecimal quantidade;

    @NotNull @Positive
    private BigDecimal precoUnitario;

    private String observacao;
}
