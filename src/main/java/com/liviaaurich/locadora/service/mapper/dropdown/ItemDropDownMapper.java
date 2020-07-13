package com.liviaaurich.locadora.service.mapper.dropdown;

import com.liviaaurich.locadora.domain.Item;
import com.liviaaurich.locadora.service.dto.dropdown.DropdownDTO;
import com.liviaaurich.locadora.service.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemDropDownMapper extends EntityMapper<DropdownDTO, Item> {
    @Override
    @Mapping(source = "numeroSerie", target = "label")
    @Mapping(source = "id", target = "value")
    DropdownDTO toDto(Item item);
}
