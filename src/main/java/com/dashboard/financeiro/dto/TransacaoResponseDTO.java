package com.dashboard.financeiro.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TransacaoResponseDTO {
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private String tipo;
    private String categoria;
    private LocalDate data;
}
