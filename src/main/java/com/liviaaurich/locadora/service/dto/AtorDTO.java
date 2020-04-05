package com.liviaaurich.locadora.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AtorDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;


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
