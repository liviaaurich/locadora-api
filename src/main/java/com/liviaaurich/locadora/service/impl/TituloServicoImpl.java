package com.liviaaurich.locadora.service.impl;

import com.liviaaurich.locadora.domain.Titulo;
import com.liviaaurich.locadora.repository.TituloRepository;
import com.liviaaurich.locadora.service.TituloServico;
import com.liviaaurich.locadora.service.dto.TituloDTO;
import com.liviaaurich.locadora.service.filtros.TituloFiltro;
import com.liviaaurich.locadora.service.mapper.TituloMapper;
import com.liviaaurich.locadora.web.rest.errors.BadRequestAlertException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;

@Service
@Transactional
@RequiredArgsConstructor
public class TituloServicoImpl implements TituloServico {

    private static final String MSG_TITULO_INEXISTENTE = "Não foi possível obter o Titulo. ID não está presente.";

    private final TituloRepository tituloRepository;
    private final TituloMapper tituloMapper;

    @Override
    public TituloDTO salvar(TituloDTO tituloDTO) {
        Titulo titulo = tituloMapper.toEntity(tituloDTO);

        tituloRepository.save(titulo);

        return tituloMapper.toDto(titulo);
    }

    @Override
    public void excluir(Long id) {
        Titulo titulo = tituloRepository.findById(id).orElseThrow(() ->
            new BadRequestAlertException(MSG_TITULO_INEXISTENTE, ENTITY_NAME, "id"));

        tituloRepository.delete(titulo);
    }

    @Override
    public Page<TituloDTO> obterTodos(TituloFiltro filtro, Pageable pageable) {
        Page<Titulo> resultado = this.tituloRepository.findAll(filtro.filter(), pageable);

        return resultado.map(tituloMapper::toDto);
    }
}
