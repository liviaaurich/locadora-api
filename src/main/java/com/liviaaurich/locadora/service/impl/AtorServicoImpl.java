package com.liviaaurich.locadora.service.impl;

import com.liviaaurich.locadora.domain.Ator;
import com.liviaaurich.locadora.repository.AtorRepository;
import com.liviaaurich.locadora.service.AtorServico;
import com.liviaaurich.locadora.service.dto.AtorDTO;
import com.liviaaurich.locadora.service.filtros.AtorFiltro;
import com.liviaaurich.locadora.service.mapper.AtorMapper;
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
public class AtorServicoImpl implements AtorServico {

    private static final String MSG_ATOR_INEXISTENTE = "Não foi possível obter o Ator. ID não está presente.";

    private final AtorRepository atorRepository;
    private final AtorMapper atorMapper;

    @Override
    public AtorDTO salvar(AtorDTO atorDTO) {
        Ator ator = atorMapper.toEntity(atorDTO);

        atorRepository.save(ator);

        return atorMapper.toDto(ator);
    }

    @Override
    public void excluir(Long id) {
        Ator ator = atorRepository.findById(id).orElseThrow(() ->
            new BadRequestAlertException(MSG_ATOR_INEXISTENTE, ENTITY_NAME, "id"));

        atorRepository.delete(ator);
    }

    @Override
    public Page<AtorDTO> obterTodos(AtorFiltro filtro, Pageable pageable) {
        Page<Ator> resultado = this.atorRepository.findAll(filtro.filter(), pageable);

        return resultado.map(atorMapper::toDto);
    }
}
