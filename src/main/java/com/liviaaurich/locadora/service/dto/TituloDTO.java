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
public class TituloDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String ano;
    private String sinopse;
    private CategoriaDTO categoria;

    private Long idClasse;
    private String descricaoClasse;

    private Long idDiretor;
    private String descricaoDiretor;

    private List<RelAtorTituloDTO> listaAtores = new ArrayList<>();


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
