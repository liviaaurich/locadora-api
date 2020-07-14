package com.liviaaurich.locadora.service.mapper;

import com.liviaaurich.locadora.domain.Item;
import com.liviaaurich.locadora.domain.Titulo;
import com.liviaaurich.locadora.service.dto.ItemDTO;
import com.liviaaurich.locadora.service.dto.TituloDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper extends EntityMapper<ItemDTO, Item> {

    /**
     * {@inheritDoc}
     */
    @Override
    @Mapping(source = "idTitulo", target = "titulo.id")
    Item toEntity(ItemDTO itemDTO);

    /**
     * {@inheritDoc}
     */
    @Override
    @Mapping(target = "idTitulo", source = "titulo.id")
    @Mapping(target = "descricaoTitulo", source = "titulo.nome")
    ItemDTO toDto(Item item);
}
