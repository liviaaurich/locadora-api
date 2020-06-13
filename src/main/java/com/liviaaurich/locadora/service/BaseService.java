package com.liviaaurich.locadora.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<T> {

    T salvar(T ator);

    void excluir(Long id);

    Page<T> obterTodos(T dto, Pageable pageable);
}
