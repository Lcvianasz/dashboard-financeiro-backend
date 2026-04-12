package com.dashboard.financeiro.service;

import com.dashboard.financeiro.model.Transacao;
import com.dashboard.financeiro.repository.TransacaoRepository;
import org.springframework.stereotype.Service;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Service
public class TrasacaoService {

    private final TransacaoRepository transacaoRepository;

    public Transacao salvar(Transacao t){
        return transacaoRepository.save(t);
    }
    public List<Transacao> listar(){
        return transacaoRepository.findAll();
    }
    public void deletar(Long id) {
        transacaoRepository.deleteById(id);
    }
}
