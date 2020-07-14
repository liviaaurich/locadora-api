package com.liviaaurich.locadora.service.mapper;

import com.liviaaurich.locadora.domain.Categoria;
import com.liviaaurich.locadora.service.dto.CategoriaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaMapper extends EntityMapper<CategoriaDTO, Categoria> {
}
