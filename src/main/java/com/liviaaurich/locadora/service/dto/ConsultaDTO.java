package com.liviaaurich.locadora.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ConsultaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    // Título
    private Long id;
    private String nome;
    private String ano;
    private String descricaoClasse;
    private String descricaoDiretor;
    private String descricaoCategoria;
    private String sinopse;
    private List<RelAtorTituloDTO> listaAtores = new ArrayList<>();

    // Classe
    private Double valor;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        return (o instanceof ClienteDTO) && new EqualsBuilder()
            .append(getId(), ((ClienteDTO) o).getId())
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

