package com.liviaaurich.locadora.service.mapper;

import com.liviaaurich.locadora.domain.Socio;
import com.liviaaurich.locadora.service.dto.SocioDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SocioMapper extends EntityMapper<SocioDTO, Socio> {
}
