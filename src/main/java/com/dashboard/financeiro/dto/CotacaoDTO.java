package com.dashboard.financeiro.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class CotacaoDTO {

    private String simbolo;
    private BigDecimal preco;
    private BigDecimal variacaoPercentual;
    private String nomeEmpresa;
}
