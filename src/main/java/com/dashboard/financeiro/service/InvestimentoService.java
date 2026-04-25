package com.dashboard.financeiro.service;

import com.dashboard.financeiro.dto.CarteiraResponseDTO;
import com.dashboard.financeiro.dto.CompraVendaRequestDTO;
import com.dashboard.financeiro.dto.CotacaoDTO;
import com.dashboard.financeiro.dto.PosicaoResponseDTO;
import com.dashboard.financeiro.model.*;
import com.dashboard.financeiro.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InvestimentoService {

    private final CarteiraRepository carteiraRepository;
    private final AtivoService ativoService;
    private final PosicaoRepository posicaoRepository;
    private final TransacaoInvestimentoRepository transacaoRepository;
    private final BrapiService brapiService;

    @Transactional
    public void comprar(Long usuarioId, CompraVendaRequestDTO request) {
        Carteira carteira = getCarteiraByUsuarioId(usuarioId);
        Ativo ativo = ativoService.buscarOuCriar(request.getSimbolo());

        TransacaoInvestimento transacao = new TransacaoInvestimento();
        transacao.setCarteira(carteira);
        transacao.setAtivo(ativo);
        transacao.setTipo(TipoTransacaoInvestimento.COMPRA);
        transacao.setQuantidade(request.getQuantidade());
        transacao.setPrecoUnitario(request.getPrecoUnitario());
        transacao.setValorTotal(request.getPrecoUnitario().multiply(request.getQuantidade()));
        transacao.setObservacao(request.getObservacao());
        transacaoRepository.save(transacao);

        // Atualiza posição (preço médio ponderado e quantidade)
        Posicao posicao = posicaoRepository.findByCarteiraAndAtivo(carteira, ativo)
                .orElse(new Posicao());
        if (posicao.getId() == null) {
            posicao.setCarteira(carteira);
            posicao.setAtivo(ativo);
            posicao.setQuantidade(BigDecimal.ZERO);
            posicao.setPrecoMedioCompra(BigDecimal.ZERO);
        }

        BigDecimal novaQuantidade = posicao.getQuantidade().add(request.getQuantidade());
        BigDecimal novoPrecoMedio = calcularPrecoMedioPonderado(
                posicao.getPrecoMedioCompra(), posicao.getQuantidade(),
                request.getPrecoUnitario(), request.getQuantidade());
        posicao.setQuantidade(novaQuantidade);
        posicao.setPrecoMedioCompra(novoPrecoMedio);
        posicaoRepository.save(posicao);
    }

    @Transactional
    public void vender(Long usuarioId, CompraVendaRequestDTO request) {
        Carteira carteira = getCarteiraByUsuarioId(usuarioId);
        Ativo ativo = ativoService.buscarOuCriar(request.getSimbolo());

        Posicao posicao = posicaoRepository.findByCarteiraAndAtivo(carteira, ativo)
                .orElseThrow(() -> new RuntimeException("Você não possui este ativo na carteira"));

        if (posicao.getQuantidade().compareTo(request.getQuantidade()) < 0) {
            throw new RuntimeException("Quantidade vendida maior do que a disponível");
        }

        TransacaoInvestimento transacao = new TransacaoInvestimento();
        transacao.setCarteira(carteira);
        transacao.setAtivo(ativo);
        transacao.setTipo(TipoTransacaoInvestimento.VENDA);
        transacao.setQuantidade(request.getQuantidade());
        transacao.setPrecoUnitario(request.getPrecoUnitario());
        transacao.setValorTotal(request.getPrecoUnitario().multiply(request.getQuantidade()));
        transacao.setObservacao(request.getObservacao());
        transacaoRepository.save(transacao);

        BigDecimal novaQuantidade = posicao.getQuantidade().subtract(request.getQuantidade());
        if (novaQuantidade.compareTo(BigDecimal.ZERO) == 0) {
            posicaoRepository.delete(posicao);
        } else {
            posicao.setQuantidade(novaQuantidade);
            posicaoRepository.save(posicao);
        }
    }

    public CarteiraResponseDTO obterCarteiraComCotacoes(Long usuarioId) {
        Carteira carteira = getCarteiraByUsuarioId(usuarioId);
        List<Posicao> posicoes = carteira.getPosicoes();

        List<PosicaoResponseDTO> posicoesDTO = new ArrayList<>();
        for (Posicao pos : posicoes) {
            try {
                CotacaoDTO cotacao = brapiService.buscarCotacao(pos.getAtivo().getSimbolo()).block();
                if (cotacao != null) {
                    BigDecimal precoAtual = cotacao.getPreco();
                    BigDecimal valorInvestido = pos.getPrecoMedioCompra().multiply(pos.getQuantidade());
                    BigDecimal valorAtual = precoAtual.multiply(pos.getQuantidade());
                    BigDecimal lucroPrejuizo = valorAtual.subtract(valorInvestido);
                    BigDecimal rentabilidade = valorInvestido.compareTo(BigDecimal.ZERO) > 0 ?
                            lucroPrejuizo.divide(valorInvestido, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)) : BigDecimal.ZERO;

                    posicoesDTO.add(PosicaoResponseDTO.builder()
                            .simbolo(pos.getAtivo().getSimbolo())
                            .nomeEmpresa(cotacao.getNomeEmpresa())
                            .quantidade(pos.getQuantidade())
                            .precoMedioCompra(pos.getPrecoMedioCompra())
                            .precoAtual(precoAtual)
                            .valorTotalInvestido(valorInvestido)
                            .valorAtual(valorAtual)
                            .lucroPrejuizo(lucroPrejuizo)
                            .rentabilidadePercentual(rentabilidade)
                            .build());
                }
            } catch (Exception e) {
                log.error("Erro ao buscar cotação para {}", pos.getAtivo().getSimbolo(), e);
            }
        }

        return CarteiraResponseDTO.builder()
                .id(carteira.getId())
                .nome(carteira.getNome())
                .descricao(carteira.getDescricao())
                .dataCriacao(carteira.getDataCriacao())
                .posicoes(posicoesDTO)
                .build();
    }

    private Carteira getCarteiraByUsuarioId(Long usuarioId) {
        return carteiraRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não possui uma carteira. Crie uma primeiro."));
    }

    private BigDecimal calcularPrecoMedioPonderado(BigDecimal precoAntigo, BigDecimal qtdAntiga,
                                                   BigDecimal precoNovo, BigDecimal qtdNova) {
        if (qtdAntiga.compareTo(BigDecimal.ZERO) == 0) return precoNovo;
        BigDecimal totalValorAntigo = precoAntigo.multiply(qtdAntiga);
        BigDecimal totalValorNovo = precoNovo.multiply(qtdNova);
        return totalValorAntigo.add(totalValorNovo).divide(qtdAntiga.add(qtdNova), 2, RoundingMode.HALF_UP);
    }
}