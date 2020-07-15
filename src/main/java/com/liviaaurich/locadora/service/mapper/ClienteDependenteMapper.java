package com.liviaaurich.locadora.service.mapper;

import com.liviaaurich.locadora.domain.Dependente;
import com.liviaaurich.locadora.service.dto.ClienteDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteDependenteMapper extends EntityMapper<ClienteDTO, Dependente> {
}
