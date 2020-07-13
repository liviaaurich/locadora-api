package com.liviaaurich.locadora.service.dto;

import com.liviaaurich.locadora.enumeration.TipoItemEnum;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class ItemDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String numeroSerie;
    private TipoItemEnum tipoItem;
    private LocalDateTime dataAquisicao;
    private Long idTitulo;
    private String descricaoTitulo;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        return (o instanceof AtorDTO) && new EqualsBuilder()
            .append(getId(), ((AtorDTO) o).getId())
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
