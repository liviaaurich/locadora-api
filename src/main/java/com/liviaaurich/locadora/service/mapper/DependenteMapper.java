package com.liviaaurich.locadora.service.mapper;

import com.liviaaurich.locadora.domain.Dependente;
import com.liviaaurich.locadora.service.dto.DependenteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DependenteMapper extends EntityMapper<DependenteDTO, Dependente> {

    /**
     * {@inheritDoc}
     */
    @Override
    @Mapping(source = "idSocio", target = "socio.id")
    Dependente toEntity(DependenteDTO dependenteDTO);

    /**
     * {@inheritDoc}
     */
    @Override
    @Mapping(target = "idSocio", source = "socio.id")
    DependenteDTO toDto(Dependente dependente);
}
