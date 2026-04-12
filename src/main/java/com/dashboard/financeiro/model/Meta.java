package com.dashboard.financeiro.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Meta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoria;
    private Double valorMeta;
    private Integer mes;
    private Integer ano;
}
