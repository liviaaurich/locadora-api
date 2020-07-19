package com.liviaaurich.locadora.service;

import com.liviaaurich.locadora.service.dto.CategoriaDTO;
import com.liviaaurich.locadora.service.dto.dropdown.DropdownDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {

    T salvar(T ator);

    void excluir(Long id);

    Page<T> obterTodos(T dto, Pageable pageable);

    Optional<T> obterPorId(Long id);

    default List<DropdownDTO> obterDropdown(){
        throw new NotImplementedException();
    }

    default List<CategoriaDTO> obterCategorias(){
        throw new NotImplementedException();
    }
}
