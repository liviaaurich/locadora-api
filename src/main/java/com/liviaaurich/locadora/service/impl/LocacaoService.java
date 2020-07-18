package com.liviaaurich.locadora.service.impl;

import com.liviaaurich.locadora.domain.Locacao;
import com.liviaaurich.locadora.repository.LocacaoRepository;
import com.liviaaurich.locadora.service.BaseService;
import com.liviaaurich.locadora.service.dto.LocacaoDTO;
import com.liviaaurich.locadora.service.mapper.LocacaoMapper;
import com.liviaaurich.locadora.web.rest.errors.BadRequestAlertException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;

@Service
@Transactional
@RequiredArgsConstructor
public class LocacaoService implements BaseService<LocacaoDTO> {

    private static final String MSG_LOCACAO_INEXISTENTE = "Não foi possível obter a Locação. ID não está presente.";
    private static final String LOCACAO = "Locacao";

    private final LocacaoMapper locacaoMapper;
    private final LocacaoRepository locacaoRepository;

    @Override
    public LocacaoDTO salvar(LocacaoDTO locacaoDTO) {
        Locacao locacao = locacaoMapper.toEntity(locacaoDTO);
        locacao.setDtLocacao(LocalDateTime.now());

        if(locacao.getSocio().getId() == null) {
            locacao.setSocio(null);
        } else {
            locacao.setDependente(null);
        }
        verificarDisponibilidadeItem(locacao);

        locacaoRepository.saveAndFlush(locacao);
        return locacaoMapper.toDto(locacao);
    }

    @Override
    public void excluir(Long id) {

    }

    @Override
    public Page<LocacaoDTO> obterTodos(LocacaoDTO dto, Pageable pageable) {
        return null;
    }

    public boolean validarVinculoItem(Long id) {
        return (locacaoRepository.findAllByItemId(id).isEmpty());
    }

    public boolean validarVinculoSocio(Long id) {
        return (locacaoRepository.findAllBySocioId(id).isEmpty());
    }

    public boolean validarVinculoDependente(Long id) {
        return (locacaoRepository.findAllByDependenteId(id).isEmpty());
    }

    public void verificarDisponibilidadeItem(Locacao locacao) {
        List<Locacao> locacoesBanco = locacaoRepository.findByItemIdAndDtDevolucaoPrevistaAfter(locacao.getItem().getId(), locacao.getDtLocacao());
        if(!locacoesBanco.isEmpty()) {
            Optional<Locacao> locacaoOptional = locacoesBanco.stream().max(Comparator.comparing(Locacao::getDtDevolucaoPrevista));
            locacaoOptional.ifPresent(loc -> {
                throw new BadRequestAlertException("Item não disponível. Data provável de disponibilidade: " + loc.getDtDevolucaoPrevista().plusDays(1L), ENTITY_NAME, LOCACAO);
            });
        }
    }
}
