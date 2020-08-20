package com.liviaaurich.locadora.service.mapper;

import com.liviaaurich.locadora.domain.Titulo;
import com.liviaaurich.locadora.service.dto.ConsultaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RelAtorTituloMapper.class})
public interface ConsultaTituloMapper extends EntityMapper<ConsultaDTO, Titulo> {

    @Override
    @Mapping(target = "descricaoDiretor", source = "diretor.nome")
    @Mapping(target = "descricaoClasse", source = "classe.nome")
    @Mapping(target = "descricaoCategoria", source = "categoria.descricao")
    @Mapping(target = "valor", source = "classe.valor")
    ConsultaDTO toDto(Titulo titulo);
}
