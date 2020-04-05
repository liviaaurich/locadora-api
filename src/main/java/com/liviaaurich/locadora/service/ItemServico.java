package com.liviaaurich.locadora.service;

import com.liviaaurich.locadora.service.dto.ItemDTO;
import com.liviaaurich.locadora.service.filtros.ItemFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemServico {

    ItemDTO salvar(ItemDTO itemDTO);

    void excluir(Long id);

    Page<ItemDTO> obterTodos(ItemFiltro filtro, Pageable pageable);
}
