package com.liviaaurich.locadora.service.dto;


import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class LocacaoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private LocalDateTime dtLocacao;
    private LocalDateTime dtDevolucaoPrevista;
    private LocalDateTime dtDevolucaoEfetiva;

    private Double valorCobrado;
    private Double multaCobrada;
    private String status;

    private Long idItem;
    private Long idSocio;
    private Long idDependente;

    private String descricaoItem;
    private String descricaoSocio;
    private String descricaoDependente;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        return (o instanceof LocacaoDTO) && new EqualsBuilder()
            .append(getId(), ((LocacaoDTO) o).getId())
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
