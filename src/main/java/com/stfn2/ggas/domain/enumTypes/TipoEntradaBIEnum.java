package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TipoEntradaBIEnum {

    PRIMITIVO(1L, "Primitivo", "Primi"),
    ENTIDADE(2L, "Entidade", "Ent"),
    DEFINIDOS(3L, "Definidos", "Def"),
    MULTIPLAS_ENTIDADE(4L, "Multiplas Entidade", "Mult Ent"),
    ANO_MES(5L, "Ano Mês", "AnoMês"),;


    private final Long id;
    private final String descricao;
    private final String abreviado;

    TipoEntradaBIEnum(Long id, String descricao, String abreviado) {
        this.id = id;
        this.descricao = descricao;
        this.abreviado = abreviado;
    }

    public static TipoEntradaBIEnum toEnum(Long id) {
        if (id == null) {
            return null;
        }

        for (TipoEntradaBIEnum x : TipoEntradaBIEnum.values()) {
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