package com.liviaaurich.locadora.service;

import com.liviaaurich.locadora.service.dto.dropdown.DropdownDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseService<T> {

    T salvar(T ator);

    void excluir(Long id);

    Page<T> obterTodos(T dto, Pageable pageable);

    List<DropdownDTO> obterDropdown();
}
