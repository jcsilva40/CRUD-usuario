package com.stfn2.ggas.domain.enumTypes;

import lombok.Getter;

@Getter
public enum TransdutorPressaoTipoEnum {

    MANOMETRICO(1L, "MAN");

    private Long id;
    private String abreviado;

    private TransdutorPressaoTipoEnum(Long id, String abreviado){
        this.id = id;
        this.abreviado = abreviado;

    }

    public static TransdutorPressaoTipoEnum toEnum(Long id) {
        if(id==null) {
            return null;
        }

        for (TransdutorPressaoTipoEnum x : TransdutorPressaoTipoEnum.values()) {
            if(id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}
