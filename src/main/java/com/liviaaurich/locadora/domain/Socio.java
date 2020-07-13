package com.liviaaurich.locadora.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "TB_SOCIO")
public class Socio extends Cliente{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SOCIO")
    @SequenceGenerator(name = "SEQ_SOCIO", allocationSize = 1, sequenceName = "SEQ_SOCIO")
    @Column(name = "ID")
    private Long id;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "ENDERECO")
    private String endereco;

    @Column(name = "TELEFONE")
    private String telefone;

    @OneToMany(mappedBy = "socio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dependente> dependentes = new ArrayList<>();
}
