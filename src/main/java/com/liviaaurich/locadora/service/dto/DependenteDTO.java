package com.liviaaurich.locadora.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class DependenteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long idSocio;

    private String nome;
    private LocalDateTime dtNascimento;
    private String sexo;
    private Boolean status;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        return (o instanceof SocioDTO) && new EqualsBuilder()
            .append(getId(), ((SocioDTO) o).getId())
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
