package com.stfn2.ggas.domain.enumTypes;

import lombok.Getter;

@Getter
public enum MostradorTipoEnum {

    ANALOGICO(1L),
    DIGITAL(2L);

    private Long id;

    private MostradorTipoEnum(Long id) {
        this.id = id;
    }

    public static MostradorTipoEnum toEnum(Long id) {
        if(id==null) {
            return null;
        }

        for (MostradorTipoEnum x : MostradorTipoEnum.values()) {
            if(id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}
