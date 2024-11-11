package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;


@Getter
public enum TipoPeriodicidadeEnum {

    MENSAL(82L, "MENSAL"),
    A_CADA_FATURAMENTO(83L, "A CADA FATURAMENTO");


    private Long id;
    private String descricao;

    private TipoPeriodicidadeEnum(Long id, String descricao) {
        this.id = id;
        this.descricao=descricao;
    }

    @JsonValue
    public Long getId() {
        return id;
    }


    public static TipoPeriodicidadeEnum toEnum(Long id) {
        if(id==null) {
            return null;
        }

        for (TipoPeriodicidadeEnum x : TipoPeriodicidadeEnum.values()) {
            if(id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}
