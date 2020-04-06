package com.liviaaurich.locadora.service;

import com.liviaaurich.locadora.service.dto.AtorDTO;
import com.liviaaurich.locadora.service.dto.ClasseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClasseServico {

    ClasseDTO salvar(ClasseDTO classeDTO);

    void excluir(Long id);

    Page<ClasseDTO> obterTodos(ClasseDTO filtro, Pageable pageable);
}
