package com.liviaaurich.locadora.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

@Getter
@Setter
public class CategoriaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String descricao;

    @Override
    public boolean equals(Object o) {
        return (o instanceof CategoriaDTO) && new EqualsBuilder()
            .append(getId(), ((CategoriaDTO) o).getId())
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }
}
