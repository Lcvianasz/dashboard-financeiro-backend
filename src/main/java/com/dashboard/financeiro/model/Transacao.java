package com.dashboard.financeiro.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private Double valor;
    @Enumerated(EnumType.STRING)
    private TipoTransacao tipo;

    private String categoria;

    private LocalDate data;
}
