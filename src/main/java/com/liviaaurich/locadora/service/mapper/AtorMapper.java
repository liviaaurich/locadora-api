package com.liviaaurich.locadora.service.mapper;

import com.liviaaurich.locadora.domain.Ator;
import com.liviaaurich.locadora.service.dto.AtorDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AtorMapper extends EntityMapper<AtorDTO, Ator> {
}
