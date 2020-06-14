package com.liviaaurich.locadora.service.mapper.dropdown;

import com.liviaaurich.locadora.domain.Ator;
import com.liviaaurich.locadora.service.dto.dropdown.DropdownDTO;
import com.liviaaurich.locadora.service.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AtorDropDownMapper extends EntityMapper<DropdownDTO, Ator> {
    @Override
    @Mapping(source = "nome", target = "label")
    @Mapping(source = "id", target = "value")
    DropdownDTO toDto(Ator ator);
}
