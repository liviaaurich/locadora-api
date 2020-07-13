package com.liviaaurich.locadora.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Setter
@Getter
public class AtorTituloPK implements Serializable {

    private Long idAtor;

    private Long idTitulo;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        return (o instanceof AtorTituloPK) && new EqualsBuilder()
            .append(getIdAtor(), ((AtorTituloPK) o).getIdAtor())
            .append(getIdTitulo(), ((AtorTituloPK) o).getIdTitulo())
            .isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIdAtor())
            .append(getIdTitulo())
            .toHashCode();
    }
}
