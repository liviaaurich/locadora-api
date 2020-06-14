package com.liviaaurich.locadora.service.mapper;

import com.liviaaurich.locadora.domain.Titulo;
import com.liviaaurich.locadora.service.dto.TituloDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CategoriaMapper.class, AtorMapper.class})
public interface TituloMapper extends EntityMapper<TituloDTO, Titulo> {

    /**
     * {@inheritDoc}
     */
    @Override
    @Mapping(source = "idDiretor", target = "diretor.id")
    @Mapping(source = "idClasse", target = "classe.id")
    Titulo toEntity(TituloDTO tituloDTO);

    /**
     * {@inheritDoc}
     */
    @Override
    @Mapping(target = "idDiretor", source = "diretor.id")
    @Mapping(target = "idClasse", source = "classe.id")
    TituloDTO toDto(Titulo titulo);
}
