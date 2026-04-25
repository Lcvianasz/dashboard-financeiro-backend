package com.dashboard.financeiro.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class CarteiraResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
    private LocalDateTime dataCriacao;
    private List<PosicaoResponseDTO> posicoes;
}
