package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TipoPrimitivoParametroBIEnum {


    SHORT(1L, "Short", "Short"),
    STRING(2L, "String", "String"),
    LONG(3L, "Long", "Long"),
    BIG_DECIMAL(4L, "BigDecimal", "BigDecimal"),
    INTEGER(5L, "Integer", "Integer"),
    DATE(6L, "Date", "Date"),
    DOUBLE(7L, "Double", "Double");


    private final Long id;
    private final String descricao;
    private final String abreviado;

    TipoPrimitivoParametroBIEnum(Long id, String descricao, String abreviado) {
        this.id = id;
        this.descricao = descricao;
        this.abreviado = abreviado;
    }

    public static TipoPrimitivoParametroBIEnum toEnum(Long id) {
        if (id == null) {
            return null;
        }

        for (TipoPrimitivoParametroBIEnum x : TipoPrimitivoParametroBIEnum.values()) {
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