package com.liviaaurich.locadora.service.mapper.dropdown;

import com.liviaaurich.locadora.domain.Diretor;
import com.liviaaurich.locadora.service.dto.dropdown.DropdownDTO;
import com.liviaaurich.locadora.service.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DiretorDropdownMapper extends EntityMapper<DropdownDTO, Diretor> {
    @Override
    @Mapping(source = "nome", target = "label")
    @Mapping(source = "id", target = "value")
    DropdownDTO toDto(Diretor diretor);
}
