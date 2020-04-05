package com.liviaaurich.locadora.service.mapper;

import com.liviaaurich.locadora.domain.Titulo;
import com.liviaaurich.locadora.service.dto.TituloDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface TituloMapper extends EntityMapper<TituloDTO, Titulo> {
}
