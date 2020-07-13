package com.liviaaurich.locadora.service.impl;

import com.liviaaurich.locadora.domain.Ator;
import com.liviaaurich.locadora.repository.AtorRepository;
import com.liviaaurich.locadora.repository.RelAtorTituloRepository;
import com.liviaaurich.locadora.repository.TituloRepository;
import com.liviaaurich.locadora.service.AtorServico;
import com.liviaaurich.locadora.service.BaseService;
import com.liviaaurich.locadora.service.dto.AtorDTO;
import com.liviaaurich.locadora.service.dto.dropdown.DropdownDTO;
import com.liviaaurich.locadora.service.mapper.AtorMapper;
import com.liviaaurich.locadora.service.mapper.dropdown.AtorDropDownMapper;
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
public class AtorServicoImpl implements BaseService<AtorDTO> {

    private static final String MSG_ATOR_INEXISTENTE = "Não foi possível obter o Ator. ID não está presente.";
    private static final String ATOR = "Ator";

    private final AtorRepository atorRepository;
    private final AtorMapper atorMapper;
    private final AtorDropDownMapper atorDropDownMapper;
    private final RelAtorTituloRepository relAtorTituloRepository;

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

        if(!relAtorTituloRepository.findAllByIdIdAtor(ator.getId()).isEmpty()) {
            throw new BadRequestAlertException("O Ator selecionado está vinculado a um Título.", ENTITY_NAME, ATOR);
        }

        atorRepository.delete(ator);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AtorDTO> obterTodos(AtorDTO filtro, Pageable pageable) {
        return atorRepository.findByFilter(filtro, pageable);
    }

    @Override
    public List<DropdownDTO> obterDropdown() {
        return atorRepository.findAll().stream().map(atorDropDownMapper::toDto).collect(Collectors.toList());
    }
}
