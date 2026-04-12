package com.dashboard.financeiro.service;

import com.dashboard.financeiro.model.Meta;
import com.dashboard.financeiro.repository.MetaRepository;
import org.springframework.stereotype.Service;
import lombok.*;

import java.util.List;

@Service
@AllArgsConstructor
public class MetaService {
    private final MetaRepository metaRepository;

    public Meta salvar(Meta meta){
        return metaRepository.save(meta);
    }
    public List<Meta> listar(int mes, int ano){
        return metaRepository.findByMesAndAno(mes, ano);
    }
}
