package com.liviaaurich.locadora.service.impl;

import com.liviaaurich.locadora.domain.Item;
import com.liviaaurich.locadora.repository.ItemRepository;
import com.liviaaurich.locadora.service.LocacaoService;
import com.liviaaurich.locadora.service.dto.ClasseDTO;
import com.liviaaurich.locadora.service.mapper.ClasseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LocacaoServicoImpl implements LocacaoService {

    private static final String MSG_LOCACAO_INEXISTENTE = "Não foi possível obter a Locação. ID não está presente.";
    private static final String LOCACAO = "Locacao";

    private final ClasseMapper classeMapper;
    private final ItemRepository itemRepository;

    @Override
    public ClasseDTO obterClasseByItem(Long idItem) {
        Item item = itemRepository.getOne(idItem);

        return classeMapper.toDto(item.getTitulo().getClasse());
    }
}
