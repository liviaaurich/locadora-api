package com.liviaaurich.locadora.service.mapper.dropdown;

import com.liviaaurich.locadora.domain.Titulo;
import com.liviaaurich.locadora.service.dto.dropdown.DropdownDTO;
import com.liviaaurich.locadora.service.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TituloDropdownMapper extends EntityMapper<DropdownDTO, Titulo> {

    @Override
    @Mapping(source = "nome", target = "label")
    @Mapping(source = "id", target = "value")
    DropdownDTO toDto(Titulo titulo);
}
