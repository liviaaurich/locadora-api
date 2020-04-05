package com.liviaaurich.locadora.service.impl;

import com.liviaaurich.locadora.domain.Diretor;
import com.liviaaurich.locadora.repository.DiretorRepository;
import com.liviaaurich.locadora.service.DiretorServico;
import com.liviaaurich.locadora.service.dto.DiretorDTO;
import com.liviaaurich.locadora.service.filtros.DiretorFiltro;
import com.liviaaurich.locadora.service.mapper.DiretorMapper;
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
public class DiretorServicoImpl implements DiretorServico {

    private static final String MSG_DIRETOR_INEXISTENTE = "Não foi possível obter o Diretor. ID não está presente.";

    private final DiretorRepository diretorRepository;
    private final DiretorMapper diretorMapper;

    @Override
    public DiretorDTO salvar(DiretorDTO diretorDTO) {
        Diretor diretor = diretorMapper.toEntity(diretorDTO);

        diretorRepository.save(diretor);

        return diretorMapper.toDto(diretor);
    }

    @Override
    public void excluir(Long id) {
        Diretor diretor = diretorRepository.findById(id).orElseThrow(() ->
            new BadRequestAlertException(MSG_DIRETOR_INEXISTENTE, ENTITY_NAME, "id"));

        diretorRepository.delete(diretor);
    }

    @Override
    public Page<DiretorDTO> obterTodos(DiretorFiltro filtro, Pageable pageable) {
        Page<Diretor> resultado = this.diretorRepository.findAll(filtro.filter(), pageable);

        return resultado.map(diretorMapper::toDto);
    }
}
