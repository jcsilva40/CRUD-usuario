package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum FormaCobrancaParcelamentoEnum {

    NOTA_DE_DEBITO(48L, "NOTA DE DÉBITO", ""),
    FATURA(49L, "FATURA", "");


    private final Long id;
    private final String descricao;
    private final String abreviado;

    FormaCobrancaParcelamentoEnum(Long id, String descricao, String abreviado) {
        this.id = id;
        this.descricao = descricao;
        this.abreviado = abreviado;
    }

    @JsonValue
    public Long getId() {
        return id;
    }

    public static FormaCobrancaParcelamentoEnum toEnum(Long id) {
        if(id==null) {
            return null;
        }

        for (FormaCobrancaParcelamentoEnum x : FormaCobrancaParcelamentoEnum.values()) {
            if(id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}
