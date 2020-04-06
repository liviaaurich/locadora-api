package com.liviaaurich.locadora.service;

import com.liviaaurich.locadora.service.dto.AtorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AtorServico {

    AtorDTO salvar(AtorDTO atorDTO);

    void excluir(Long id);

    Page<AtorDTO> obterTodos(AtorDTO filtro, Pageable pageable);

}
