package com.liviaaurich.locadora.service.mapper;

import com.liviaaurich.locadora.domain.Classe;
import com.liviaaurich.locadora.service.dto.ClasseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface ClasseMapper extends EntityMapper<ClasseDTO, Classe> {
}
