package com.liviaaurich.locadora.service.mapper.dropdown;

import com.liviaaurich.locadora.domain.Classe;
import com.liviaaurich.locadora.service.dto.dropdown.DropdownDTO;
import com.liviaaurich.locadora.service.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClasseDropdownMapper extends EntityMapper<DropdownDTO, Classe> {
    @Override
    @Mapping(source = "nome", target = "label")
    @Mapping(source = "id", target = "value")
    DropdownDTO toDto(Classe classe);
}
