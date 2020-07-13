package com.liviaaurich.locadora.service.impl;

import com.liviaaurich.locadora.domain.Item;
import com.liviaaurich.locadora.repository.ItemRepository;
import com.liviaaurich.locadora.service.BaseService;
import com.liviaaurich.locadora.service.ItemServico;
import com.liviaaurich.locadora.service.dto.ItemDTO;
import com.liviaaurich.locadora.service.dto.dropdown.DropdownDTO;
import com.liviaaurich.locadora.service.mapper.ItemMapper;
import com.liviaaurich.locadora.service.mapper.dropdown.ItemDropDownMapper;
import com.liviaaurich.locadora.service.mapper.dropdown.TituloDropdownMapper;
import com.liviaaurich.locadora.web.rest.errors.BadRequestAlertException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemServicoImpl implements BaseService<ItemDTO> {

    private static final String MSG_ITEM_INEXISTENTE = "Não foi possível obter o Item. ID não está presente.";

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final ItemDropDownMapper itemDropDownMapper;

    @Override
    public ItemDTO salvar(ItemDTO itemDTO) {
        Item item = itemMapper.toEntity(itemDTO);

        itemRepository.save(item);

        return itemMapper.toDto(item);
    }

    @Override
    public void excluir(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() ->
            new BadRequestAlertException(MSG_ITEM_INEXISTENTE, ENTITY_NAME, "id"));

        itemRepository.delete(item);
    }

    @Override
    public Page<ItemDTO> obterTodos(ItemDTO dto, Pageable pageable) {
        return itemRepository.findAll(pageable).map(itemMapper::toDto);
    }

    @Override
    public List<DropdownDTO> obterDropdown() {
        return itemRepository.findAll().stream().map(itemDropDownMapper::toDto).collect(Collectors.toList());
    }
}
