package com.dashboard.financeiro.service;

import com.dashboard.financeiro.model.Ativo;
import com.dashboard.financeiro.repository.AtivoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AtivoService {

    private final AtivoRepository ativoRepository;

    public Ativo buscarOuCriar(String simbolo) {
        return ativoRepository.findBySimbolo(simbolo)
                .orElseGet(() -> {
                    Ativo novo = new Ativo();
                    novo.setSimbolo(simbolo);
                    novo.setNomeEmpresa(simbolo);
                    return ativoRepository.save(novo);
                });
    }
}
