package com.liviaaurich.locadora.service.mapper;

import com.liviaaurich.locadora.domain.Diretor;
import com.liviaaurich.locadora.service.dto.DiretorDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiretorMapper extends EntityMapper<DiretorDTO, Diretor> {
}
