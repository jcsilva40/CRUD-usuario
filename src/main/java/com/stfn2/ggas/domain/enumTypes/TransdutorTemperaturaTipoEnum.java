package com.stfn2.ggas.domain.enumTypes;

import lombok.Getter;

@Getter
public enum TransdutorTemperaturaTipoEnum {

    PT_100(1L, "PT-100");

    private Long id;
    private String abreviado;

    private TransdutorTemperaturaTipoEnum(Long id, String abreviado){
        this.id = id;
        this.abreviado = abreviado;

    }

    public static TransdutorTemperaturaTipoEnum toEnum(Long id) {
        if(id==null) {
            return null;
        }

        for (TransdutorTemperaturaTipoEnum x : TransdutorTemperaturaTipoEnum.values()) {
            if(id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}
