package com.liviaaurich.locadora.service;

import com.liviaaurich.locadora.service.dto.ItemDTO;

public interface ItemServico {

    ItemDTO salvar(ItemDTO itemDTO);

    void excluir(Long id);
}
