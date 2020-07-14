package com.liviaaurich.locadora.service.impl;

import com.liviaaurich.locadora.domain.Socio;
import com.liviaaurich.locadora.repository.SocioRepository;
import com.liviaaurich.locadora.service.BaseService;
import com.liviaaurich.locadora.service.dto.SocioDTO;
import com.liviaaurich.locadora.service.dto.dropdown.DropdownDTO;
import com.liviaaurich.locadora.service.mapper.SocioMapper;
import com.liviaaurich.locadora.web.rest.errors.BadRequestAlertException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;

@Service
@Transactional
@RequiredArgsConstructor
public class SocioServicoImpl implements BaseService<SocioDTO> {

    private static final String MSG_SOCIO_INEXISTENTE = "Não foi possível obter o Socio. ID não está presente.";
    private static final String SOCIO = "Socio";

    private final SocioRepository socioRepository;
    private final SocioMapper socioMapper;


    @Override
    public SocioDTO salvar(SocioDTO socioDTO) {
        Socio socio = socioMapper.toEntity(socioDTO);

        socioRepository.save(socio);

        return socioMapper.toDto(socio);
    }

    @Override
    public void excluir(Long id) {
        Socio socio = socioRepository.findById(id).orElseThrow(() ->
            new BadRequestAlertException(MSG_SOCIO_INEXISTENTE, ENTITY_NAME, "id"));

        socioRepository.delete(socio);
    }

    @Override
    public Page<SocioDTO> obterTodos(SocioDTO dto, Pageable pageable) {
        return socioRepository.findAll(pageable).map(socioMapper::toDto);
    }

    @Override
    public List<DropdownDTO> obterDropdown() {
        return null;
    }
}
