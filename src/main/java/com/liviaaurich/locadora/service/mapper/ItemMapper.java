package com.liviaaurich.locadora.service.mapper;

import com.liviaaurich.locadora.domain.Item;
import com.liviaaurich.locadora.service.dto.ItemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface ItemMapper extends EntityMapper<ItemDTO, Item> {
}
