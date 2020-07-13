package com.liviaaurich.locadora.service.impl;

import com.liviaaurich.locadora.domain.Diretor;
import com.liviaaurich.locadora.repository.DiretorRepository;
import com.liviaaurich.locadora.repository.TituloRepository;
import com.liviaaurich.locadora.service.BaseService;
import com.liviaaurich.locadora.service.dto.DiretorDTO;
import com.liviaaurich.locadora.service.dto.dropdown.DropdownDTO;
import com.liviaaurich.locadora.service.mapper.DiretorMapper;
import com.liviaaurich.locadora.service.mapper.dropdown.DiretorDropdownMapper;
import com.liviaaurich.locadora.web.rest.errors.BadRequestAlertException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;

@Service
@Transactional
@RequiredArgsConstructor
public class DiretorServicoImpl implements BaseService<DiretorDTO> {

    private static final String MSG_DIRETOR_INEXISTENTE = "Não foi possível obter o Diretor. ID não está presente.";
    private static final String DIRETOR = "Diretor";

    private final DiretorRepository diretorRepository;
    private final DiretorMapper diretorMapper;
    private final DiretorDropdownMapper diretorDropdownMapper;
    private final TituloRepository tituloRepository;

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

        if(!tituloRepository.findAllByDiretorId(diretor.getId()).isEmpty()) {
            throw new BadRequestAlertException("O Diretor selecionado está vinculado a um Título.", ENTITY_NAME, DIRETOR);
        }

        diretorRepository.delete(diretor);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DiretorDTO> obterTodos(DiretorDTO filtro, Pageable pageable) {
        return diretorRepository.findByFilter(filtro, pageable);
    }

    @Override
    public List<DropdownDTO> obterDropdown() {
        return diretorRepository.findAll().stream().map(diretorDropdownMapper::toDto).collect(Collectors.toList());
    }
}
