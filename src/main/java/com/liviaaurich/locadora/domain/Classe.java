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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "TB_CLASSE")
public class Classe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLASSE")
    @SequenceGenerator(name = "SEQ_CLASSE", allocationSize = 1, sequenceName = "SEQ_CLASSE")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "VALOR")
    private Double valor;

    @Column(name = "PRAZO_DEVOLUCAO")
    private Long prazoDevolucao;

    @Override
    public boolean equals(Object o) {
        return (o instanceof Classe) && new EqualsBuilder()
            .append(getId(), ((Classe) o).getId())
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }

}
