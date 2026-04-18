package com.dashboard.financeiro.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class TransacaoRequestDTO {
    @NotBlank
    private String descricao;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotBlank
    private String tipo;
    @NotBlank
    private String categoria;
    @NotNull
    private LocalDate data;
}
