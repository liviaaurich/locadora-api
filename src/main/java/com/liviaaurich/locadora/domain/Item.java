package com.liviaaurich.locadora.domain;

import com.liviaaurich.locadora.enumeration.TipoItemEnum;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "TB_ITEM")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ITEM")
    @SequenceGenerator(name = "SEQ_ITEM", allocationSize = 1, sequenceName = "SEQ_ITEM")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NUM_SERIE")
    private String numeroSerie;

    @Enumerated(EnumType.STRING)
    @Column(name = "ITEM")
    private TipoItemEnum tipoItem;

    @Column(name = "DATA_AQUISICAO")
    private LocalDateTime dataAquisicao;

    @ManyToOne
    @JoinColumn(name = "ID_TITULO", nullable = false)
    private Titulo titulo;

    @Override
    public boolean equals(Object o) {
        return (o instanceof Item) && new EqualsBuilder()
            .append(getId(), ((Item) o).getId())
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }
}
