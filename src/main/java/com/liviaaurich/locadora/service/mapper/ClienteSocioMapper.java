package com.liviaaurich.locadora.service.mapper;

import com.liviaaurich.locadora.domain.Socio;
import com.liviaaurich.locadora.service.dto.ClienteDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteSocioMapper extends EntityMapper<ClienteDTO, Socio> {
}

