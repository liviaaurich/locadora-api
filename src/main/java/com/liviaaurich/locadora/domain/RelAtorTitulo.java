package com.liviaaurich.locadora.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "REL_ATOR_TITULO")
@Getter
@Setter
public class RelAtorTitulo implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private AtorTituloPK id;

    @MapsId("idAtor")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ATOR", referencedColumnName = "ID")
    private Ator ator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TITULO", referencedColumnName = "ID")
    @MapsId("idTitulo")
    private Titulo titulo;

    public RelAtorTitulo() {
        id = new AtorTituloPK();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        return (o instanceof RelAtorTitulo) && new EqualsBuilder()
            .append(getId(), ((RelAtorTitulo) o).getId())
            .isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }
}
