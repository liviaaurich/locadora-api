package com.liviaaurich.locadora.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "TB_CATEGORIA")
public class Categoria {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Override
    public boolean equals(Object o) {
        return (o instanceof Categoria) && new EqualsBuilder()
            .append(getId(), ((Categoria) o).getId())
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }

}
