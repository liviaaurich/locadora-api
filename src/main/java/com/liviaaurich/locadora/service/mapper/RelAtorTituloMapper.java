package com.liviaaurich.locadora.service.mapper;

import com.liviaaurich.locadora.domain.RelAtorTitulo;
import com.liviaaurich.locadora.service.dto.RelAtorTituloDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RelAtorTituloMapper extends EntityMapper<RelAtorTituloDTO, RelAtorTitulo>{

    @Override
    @Mapping(source = "idAtor", target = "ator.id")
    @Mapping(source = "idAtor", target = "id.idAtor")
    @Mapping(source = "idTitulo", target = "titulo.id")
    @Mapping(source = "idTitulo", target = "id.idTitulo")
    RelAtorTitulo toEntity(RelAtorTituloDTO relAtorTituloDTO);

    @Override
    @Mapping(target = "idAtor", source = "ator.id")
    @Mapping(target = "idTitulo", source = "titulo.id")
    RelAtorTituloDTO toDto(RelAtorTitulo relAtorTitulo);
}
