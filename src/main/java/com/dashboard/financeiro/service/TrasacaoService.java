package com.dashboard.financeiro.service;

import com.dashboard.financeiro.dto.TransacaoResponseDTO;
import com.dashboard.financeiro.dto.TransacaoRequestDTO;
import com.dashboard.financeiro.model.TipoTransacao;
import com.dashboard.financeiro.model.Transacao;
import com.dashboard.financeiro.repository.TransacaoRepository;
import org.springframework.stereotype.Service;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Service
public class TrasacaoService {

    private final TransacaoRepository transacaoRepository;

    public Transacao salvar(TransacaoRequestDTO dto){
        Transacao t = new Transacao();
        t.setDescricao(dto.getDescricao());
        t.setValor(dto.getValor());
        t.setTipo(TipoTransacao.valueOf(dto.getTipo()));
        t.setCategoria(dto.getCategoria());
        t.setData(dto.getData());

        return transacaoRepository.save(t);
    }

    public TransacaoResponseDTO toDTO(Transacao t){
        return new TransacaoResponseDTO(
                t.getId(),
                t.getDescricao(),
                t.getValor(),
                t.getTipo().name(),
                t.getCategoria(),
                t.getData()
        );
    }

    public List<TransacaoResponseDTO> listar(){
        return transacaoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public void deletar(Long id){
        if(!transacaoRepository.existsById(id)){
            throw new RuntimeException("Transação não encontrada");
        }
        transacaoRepository.deleteById(id);
    }
}
