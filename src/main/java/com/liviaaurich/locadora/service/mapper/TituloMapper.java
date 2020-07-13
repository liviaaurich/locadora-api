package com.liviaaurich.locadora.service.mapper;

import com.liviaaurich.locadora.domain.Titulo;
import com.liviaaurich.locadora.service.dto.TituloDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {RelAtorTituloMapper.class})
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
    @Mapping(target = "descricaoDiretor", source = "diretor.nome")
    @Mapping(target = "descricaoClasse", source = "classe.nome")
    TituloDTO toDto(Titulo titulo);

    @AfterMapping
    default void atualizarRelacionamentos(@MappingTarget Titulo titulo) {
        titulo.getListaAtores().forEach(ator -> ator.setTitulo(titulo));
    }
}
