package com.liviaaurich.locadora.service;

import com.liviaaurich.locadora.service.dto.ClasseDTO;

public interface LocacaoService {

    ClasseDTO obterClasseByItem(Long idItem);
}
