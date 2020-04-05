package com.liviaaurich.locadora.service;

import com.liviaaurich.locadora.service.dto.TituloDTO;
import com.liviaaurich.locadora.service.filtros.TituloFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TituloServico {

    TituloDTO salvar(TituloDTO tituloDTO);

    void excluir(Long id);

    Page<TituloDTO> obterTodos(TituloFiltro filtro, Pageable pageable);
}
