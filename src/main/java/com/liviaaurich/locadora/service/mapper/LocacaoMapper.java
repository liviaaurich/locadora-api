package com.liviaaurich.locadora.service.mapper;

import com.liviaaurich.locadora.domain.Locacao;
import com.liviaaurich.locadora.service.dto.LocacaoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LocacaoMapper extends EntityMapper<LocacaoDTO, Locacao> {

    /**
     * {@inheritDoc}
     */
    @Override
    @Mapping(source = "idItem", target = "item.id")
    @Mapping(source = "idSocio", target = "socio.id")
    @Mapping(source = "idDependente", target = "dependente.id")
    Locacao toEntity(LocacaoDTO locacaoDTO);

    /**
     * {@inheritDoc}
     */
    @Override
    @Mapping(target = "idItem", source = "item.id")
    @Mapping(target = "idSocio", source = "socio.id")
    @Mapping(target = "idDependente", source = "dependente.id")
    LocacaoDTO toDto(Locacao locacao);
}
