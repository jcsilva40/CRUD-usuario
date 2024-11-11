package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TipoQueryBIEnum {

    SQL(1L, "SQL", "SQL"),
    HQL(2L, "HQL", "HQL");

    private final Long id;
    private final String descricao;
    private final String abreviado;

    TipoQueryBIEnum(Long id, String descricao, String abreviado) {
        this.id = id;
        this.descricao = descricao;
        this.abreviado = abreviado;
    }

    public static TipoQueryBIEnum toEnum(Long id) {
        if (id == null) {
            return null;
        }

        for (TipoQueryBIEnum x : TipoQueryBIEnum.values()) {
            if (id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }

    @JsonValue
    public Long getId() {
        return id;
    }
}