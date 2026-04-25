package com.dashboard.financeiro.service;

import com.dashboard.financeiro.dto.CarteiraResponseDTO;
import com.dashboard.financeiro.model.Carteira;
import com.dashboard.financeiro.model.Usuario;
import com.dashboard.financeiro.repository.CarteiraRepository;
import com.dashboard.financeiro.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CarteiraService {

    private final CarteiraRepository carteiraRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public CarteiraResponseDTO criarCarteira(Long usuarioId, String nome, String descricao) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (carteiraRepository.existsByUsuarioId(usuarioId)) {
            throw new RuntimeException("Usuário já possui uma carteira");
        }

        Carteira carteira = new Carteira();
        carteira.setNome(nome);
        carteira.setDescricao(descricao);
        carteira.setUsuario(usuario);

        carteira = carteiraRepository.save(carteira);

        return CarteiraResponseDTO.builder()
                .id(carteira.getId())
                .nome(carteira.getNome())
                .descricao(carteira.getDescricao())
                .dataCriacao(carteira.getDataCriacao())
                .build();
    }

    public Carteira buscarPorUsuarioId(Long usuarioId) {
        return carteiraRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new RuntimeException("Carteira não encontrada"));
    }
}
