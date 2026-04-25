package com.dashboard.financeiro.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class PosicaoResponseDTO {
    private String simbolo;
    private String nomeEmpresa;
    private BigDecimal quantidade;
    private BigDecimal precoMedioCompra;
    private BigDecimal precoAtual;      // da API
    private BigDecimal valorTotalInvestido;
    private BigDecimal valorAtual;
    private BigDecimal lucroPrejuizo;
    private BigDecimal rentabilidadePercentual;
}
