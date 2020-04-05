package com.liviaaurich.locadora.service;

import com.liviaaurich.locadora.service.dto.ClasseDTO;
import com.liviaaurich.locadora.service.filtros.ClasseFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClasseServico {

    ClasseDTO salvar(ClasseDTO classeDTO);

    void excluir(Long id);

    Page<ClasseDTO> obterTodos(ClasseFiltro filtro, Pageable pageable);
}
