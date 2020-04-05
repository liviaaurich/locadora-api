package com.liviaaurich.locadora.service.impl;

import com.liviaaurich.locadora.domain.Classe;
import com.liviaaurich.locadora.repository.ClasseRepository;
import com.liviaaurich.locadora.service.ClasseServico;
import com.liviaaurich.locadora.service.dto.ClasseDTO;
import com.liviaaurich.locadora.service.filtros.ClasseFiltro;
import com.liviaaurich.locadora.service.mapper.ClasseMapper;
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
public class ClasseServicoImpl implements ClasseServico {

    private static final String MSG_CLASSE_INEXISTENTE = "Não foi possível obter a Classe. ID não está presente.";

    private final ClasseRepository classeRepository;
    private final ClasseMapper classeMapper;

    @Override
    public ClasseDTO salvar(ClasseDTO classeDTO) {
        Classe classe = classeMapper.toEntity(classeDTO);

        classeRepository.save(classe);

        return classeMapper.toDto(classe);
    }

    @Override
    public void excluir(Long id) {
        Classe classe = classeRepository.findById(id).orElseThrow(() ->
            new BadRequestAlertException(MSG_CLASSE_INEXISTENTE, ENTITY_NAME, "id"));

        classeRepository.delete(classe);
    }

    @Override
    public Page<ClasseDTO> obterTodos(ClasseFiltro filtro, Pageable pageable) {
        Page<Classe> resultado = this.classeRepository.findAll(filtro.filter(), pageable);

        return resultado.map(classeMapper::toDto);
    }
}
