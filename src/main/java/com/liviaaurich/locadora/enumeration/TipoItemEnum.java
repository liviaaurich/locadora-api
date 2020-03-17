package com.liviaaurich.locadora.enumeration;

import lombok.Getter;

@Getter
public enum TipoItemEnum {

    FITA("Fita"),
    DVD("DVD"),
    BLURAY("BLURAY");

    private String descricao;

    TipoItemEnum(String descricao) {
        this.descricao = descricao;
    }

}
