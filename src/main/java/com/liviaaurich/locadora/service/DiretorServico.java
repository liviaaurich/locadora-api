package com.liviaaurich.locadora.service;

import com.liviaaurich.locadora.service.dto.AtorDTO;
import com.liviaaurich.locadora.service.dto.DiretorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiretorServico {

    DiretorDTO salvar(DiretorDTO diretorDTO);

    void excluir(Long id);

    Page<DiretorDTO> obterTodos(DiretorDTO filtro, Pageable pageable);

}
