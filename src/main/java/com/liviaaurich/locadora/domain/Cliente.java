package com.liviaaurich.locadora.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DATA_NASCIMENTO")
    private LocalDateTime dtNascimento;

    @Column(name = "SEXO")
    private String sexo;

    @Column(name = "STATUS")
    private Boolean status;
}
