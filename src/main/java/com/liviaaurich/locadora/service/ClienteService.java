package com.liviaaurich.locadora.service;

import com.liviaaurich.locadora.service.dto.ClienteDTO;
import com.liviaaurich.locadora.service.dto.DependenteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClienteService {
    Page<DependenteDTO> obterTodosBySocio(DependenteDTO dto, Pageable pageable, Long idSocio);

    List<ClienteDTO> obterTodosClientes();
}
