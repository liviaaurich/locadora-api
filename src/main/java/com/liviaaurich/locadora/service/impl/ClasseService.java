package com.liviaaurich.locadora.service.impl;

import com.liviaaurich.locadora.domain.Classe;
import com.liviaaurich.locadora.repository.ClasseRepository;
import com.liviaaurich.locadora.service.BaseService;
import com.liviaaurich.locadora.service.dto.ClasseDTO;
import com.liviaaurich.locadora.service.dto.dropdown.DropdownDTO;
import com.liviaaurich.locadora.service.mapper.ClasseMapper;
import com.liviaaurich.locadora.service.mapper.dropdown.ClasseDropdownMapper;
import com.liviaaurich.locadora.web.rest.errors.BadRequestAlertException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;

@Service
@Transactional
@RequiredArgsConstructor
public class ClasseService implements BaseService<ClasseDTO> {

    private static final String MSG_CLASSE_INEXISTENTE = "Não foi possível obter a Classe. ID não está presente.";
    private static final String CLASSE = "Classe";

    private final ClasseRepository classeRepository;
    private final ClasseMapper classeMapper;
    private final ClasseDropdownMapper classeDropdownMapper;

    private final TituloService tituloService;

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

        if(tituloService.validarVinculoClasse(classe.getId())) {
            throw new BadRequestAlertException("A Classe selecionada está vinculada a um Título.", ENTITY_NAME, CLASSE);
        }

        classeRepository.delete(classe);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ClasseDTO> obterTodos(ClasseDTO filtro, Pageable pageable) {
        return classeRepository.findByFilter(filtro, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClasseDTO> obterPorId(Long id) {
        return classeRepository.findById(id).map(classeMapper::toDto);
    }

    @Override
    public List<DropdownDTO> obterDropdown() {
        return classeRepository.findAll().stream().map(classeDropdownMapper::toDto).collect(Collectors.toList());
    }

    public List<ClasseDTO> obterValorById(List<Long> ids) {
        return classeMapper.toDto(classeRepository.findByIdIn(ids));
    }
}
