package com.stfn2.ggas.domain.enumTypes;


import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum AmortizacaoEnum {

    PRICE(68L, "PRICE"),
    SAC(69L, "SAC");


    private Long id;
    private String descricao;

    private AmortizacaoEnum(Long id, String descricao) {
        this.id = id;
        this.descricao=descricao;
    }

    @JsonValue
    public Long getId() {
        return id;
    }


    public static AmortizacaoEnum toEnum(Long id) {
        if(id==null) {
            return null;
        }

        for (AmortizacaoEnum x : AmortizacaoEnum.values()) {
            if(id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}
