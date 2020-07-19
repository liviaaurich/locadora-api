package com.liviaaurich.locadora.service.impl;

import com.liviaaurich.locadora.domain.Socio;
import com.liviaaurich.locadora.repository.SocioRepository;
import com.liviaaurich.locadora.service.BaseService;
import com.liviaaurich.locadora.service.dto.ClienteDTO;
import com.liviaaurich.locadora.service.dto.SocioDTO;
import com.liviaaurich.locadora.service.mapper.ClienteSocioMapper;
import com.liviaaurich.locadora.service.mapper.SocioMapper;
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
public class SocioService implements BaseService<SocioDTO> {

    private static final String MSG_SOCIO_INEXISTENTE = "Não foi possível obter o Socio. ID não está presente.";
    private static final String SOCIO = "Socio";

    private final SocioRepository socioRepository;
    private final SocioMapper socioMapper;
    private final ClienteSocioMapper clienteSocioMapper;
    private final LocacaoService locacaoService;

    @Override
    public SocioDTO salvar(SocioDTO socioDTO) {
        Socio socio = socioMapper.toEntity(socioDTO);
        if(socio.getId() == null) {
            socio.setStatus(true);
        }
        socioRepository.save(socio);

        return socioMapper.toDto(socio);
    }

    @Override
    public Page<SocioDTO> obterTodos(SocioDTO dto, Pageable pageable) {
        return socioRepository.findAll(pageable).map(socioMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SocioDTO> obterPorId(Long id) {
        return Optional.empty();
    }

    @Override
    public void excluir(Long id) {
        Socio socio = socioRepository.findById(id).orElseThrow(() ->
            new BadRequestAlertException(MSG_SOCIO_INEXISTENTE, ENTITY_NAME, "id"));

        if(locacaoService.validarVinculoSocio(socio.getId())) {
            throw new BadRequestAlertException("O Sócio selecionado está vinculado a uma Locação.", ENTITY_NAME, SOCIO);
        } else if (locacaoService.validarVinculoSocioDependente(socio.getId())) {
            throw new BadRequestAlertException("O Sócio selecionado possui dependete(s) vinculado(s) a uma Locação.", ENTITY_NAME, SOCIO);
        }
        socioRepository.delete(socio);
    }

    public List<ClienteDTO> obterClientes() {
        return socioRepository.findAll().stream().map(clienteSocioMapper::toDto).collect(Collectors.toList());
    }
}
