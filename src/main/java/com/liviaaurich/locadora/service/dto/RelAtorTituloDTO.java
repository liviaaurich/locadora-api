package com.liviaaurich.locadora.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Getter
@Setter
public class RelAtorTituloDTO {

    private Long idAtor;
    private String nomeAtor;
    private Long idTitulo;

    @Override
    public boolean equals(Object o) {
        return (o instanceof RelAtorTituloDTO) && new EqualsBuilder()
            .append(getIdAtor(), ((RelAtorTituloDTO) o).getIdAtor())
            .append(getIdTitulo(), ((RelAtorTituloDTO) o).getIdTitulo())
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIdAtor())
            .append(getIdTitulo())
            .toHashCode();
    }
}
