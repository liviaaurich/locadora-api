package com.liviaaurich.locadora.service.impl;

import com.liviaaurich.locadora.domain.Dependente;
import com.liviaaurich.locadora.repository.DependenteRepository;
import com.liviaaurich.locadora.repository.SocioRepository;
import com.liviaaurich.locadora.service.BaseService;
import com.liviaaurich.locadora.service.ClienteService;
import com.liviaaurich.locadora.service.dto.ClienteDTO;
import com.liviaaurich.locadora.service.dto.DependenteDTO;
import com.liviaaurich.locadora.service.dto.dropdown.DropdownDTO;
import com.liviaaurich.locadora.service.mapper.ClienteDependenteMapper;
import com.liviaaurich.locadora.service.mapper.ClienteSocioMapper;
import com.liviaaurich.locadora.service.mapper.DependenteMapper;
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
public class DependenteServicoImpl implements BaseService<DependenteDTO>, ClienteService {

    private static final String MSG_DEPENDENTE_INEXISTENTE = "Não foi possível obter o Dependente. ID não está presente.";
    private static final String DEPENDENTE = "Dependente";

    private final DependenteRepository dependenteRepository;
    private final SocioRepository socioRepository;
    private final DependenteMapper dependenteMapper;
    private final ClienteDependenteMapper clienteDependenteMapper;
    private final ClienteSocioMapper clienteSocioMapper;

    @Override
    public DependenteDTO salvar(DependenteDTO dependenteDTO) {
        Dependente dependente = dependenteMapper.toEntity(dependenteDTO);

        verificarDependentesAtivos(dependenteDTO.getIdSocio());

        if(dependente.getId() == null) {
            dependente.setStatus(true);
        }

        dependenteRepository.save(dependente);

        return dependenteMapper.toDto(dependente);
    }

    @Override
    public void excluir(Long id) {
        Dependente dependente = dependenteRepository.findById(id).orElseThrow(() ->
            new BadRequestAlertException(MSG_DEPENDENTE_INEXISTENTE, ENTITY_NAME, "id"));

        dependenteRepository.delete(dependente);
    }

    @Override
    public Page<DependenteDTO> obterTodos(DependenteDTO dto, Pageable pageable) {
        return dependenteRepository.findAll(pageable).map(dependenteMapper::toDto);
    }

    @Override
    public List<DropdownDTO> obterDropdown() {
        return null;
    }

    @Override
    public Page<DependenteDTO> obterTodosBySocio(DependenteDTO dto, Pageable pageable, Long idSocio) {
        return dependenteRepository.findAllBySocioId(idSocio, pageable).map(dependenteMapper::toDto);
    }

    @Override
    public List<ClienteDTO> obterTodosClientes() {
        List<ClienteDTO> listaClientes = socioRepository.findAll().stream().map(clienteSocioMapper::toDto).collect(Collectors.toList());
        listaClientes.addAll(dependenteRepository.findAll().stream().map(clienteDependenteMapper::toDto).collect(Collectors.toList()));

        return listaClientes;
    }

    public void verificarDependentesAtivos(Long idSocio) {
        if(dependenteRepository.countBySocioIdAndStatus(idSocio, true) >= 3) {
            throw new BadRequestAlertException("O sócio já possui três dependentes ativos.", ENTITY_NAME, DEPENDENTE);
        }
    }
}
