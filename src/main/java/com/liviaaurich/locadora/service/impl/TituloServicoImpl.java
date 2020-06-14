package com.liviaaurich.locadora.service.impl;

import com.liviaaurich.locadora.domain.Titulo;
import com.liviaaurich.locadora.repository.CategoriaRepository;
import com.liviaaurich.locadora.repository.TituloRepository;
import com.liviaaurich.locadora.service.BaseService;
import com.liviaaurich.locadora.service.TituloServico;
import com.liviaaurich.locadora.service.dto.CategoriaDTO;
import com.liviaaurich.locadora.service.dto.TituloDTO;
import com.liviaaurich.locadora.service.dto.dropdown.DropdownDTO;
import com.liviaaurich.locadora.service.mapper.CategoriaMapper;
import com.liviaaurich.locadora.service.mapper.TituloMapper;
import com.liviaaurich.locadora.service.mapper.dropdown.TituloDropdownMapper;
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
public class TituloServicoImpl implements BaseService<TituloDTO>, TituloServico {

    private static final String MSG_TITULO_INEXISTENTE = "Não foi possível obter o Titulo. ID não está presente.";

    private final TituloRepository tituloRepository;
    private final TituloMapper tituloMapper;
    private final CategoriaMapper categoriaMapper;
    private final CategoriaRepository categoriaRepository;
    private final TituloDropdownMapper tituloDropdownMapper;

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
    public Page<TituloDTO> obterTodos(TituloDTO dto, Pageable pageable) {
        Page<Titulo> resultado = null;

        return resultado.map(tituloMapper::toDto);
    }

    @Override
    public List<DropdownDTO> obterDropdown() {
        return tituloRepository.findAll().stream().map(tituloDropdownMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<CategoriaDTO> obterNaturezas() {
        return categoriaMapper.toDto(categoriaRepository.findAll());
    }
}
