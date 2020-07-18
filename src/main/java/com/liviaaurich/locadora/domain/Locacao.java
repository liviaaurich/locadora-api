package com.liviaaurich.locadora.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "TB_LOCACAO")
public class Locacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LOCACAO")
    @SequenceGenerator(name = "SEQ_LOCACAO", allocationSize = 1, sequenceName = "SEQ_LOCACAO")
    @Column(name = "ID")
    private Long id;

    @Column(name = "DATA_LOCACAO")
    private LocalDateTime dtLocacao;

    @Column(name = "DATA_DEVOLUCAO_PREVISTA")
    private LocalDateTime dtDevolucaoPrevista;

    @Column(name = "DATA_DEVOLUCAO_EFETIVA")
    private LocalDateTime dtDevolucaoEfetiva;

    @Column(name = "VALOR_COBRADO")
    private Double valorCobrado;

//    @Column(name = "MULTA")
//    private Double multaCobrada;

    @ManyToOne
    @JoinColumn(name = "ID_ITEM", nullable = false)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "ID_SOCIO")
    private Socio socio;

    @ManyToOne
    @JoinColumn(name = "ID_DEPENDENTE")
    private Dependente dependente;

    @Override
    public boolean equals(Object o) {
        return (o instanceof Locacao) && new EqualsBuilder()
            .append(getId(), ((Locacao) o).getId())
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }
}
