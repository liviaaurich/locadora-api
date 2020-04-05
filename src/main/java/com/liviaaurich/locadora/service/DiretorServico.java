package com.liviaaurich.locadora.service;

import com.liviaaurich.locadora.service.dto.DiretorDTO;
import com.liviaaurich.locadora.service.filtros.DiretorFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiretorServico {

    DiretorDTO salvar(DiretorDTO diretorDTO);

    void excluir(Long id);

    Page<DiretorDTO> obterTodos(DiretorFiltro filtro, Pageable pageable);

}
