package com.liviaaurich.locadora.enumeration;

import lombok.Getter;

@Getter
public enum TipoItemEnum {

    FITA(0, "Fita"),
    DVD(1, "DVD"),
    BLURAY(2, "BLURAY");

    private Integer id;
    private String descricao;

    TipoItemEnum(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

}
