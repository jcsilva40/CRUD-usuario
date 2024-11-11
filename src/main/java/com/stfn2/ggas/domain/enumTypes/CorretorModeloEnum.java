package com.stfn2.ggas.domain.enumTypes;

import lombok.Getter;

@Getter
public enum CorretorModeloEnum {

    EMIA(8L, "EMIA");

    private Long id;
    private String abreviado;

    private CorretorModeloEnum(Long id, String abreviado){
        this.id = id;
        this.abreviado = abreviado;

    }

    public static CorretorModeloEnum toEnum(Long id) {
        if(id==null) {
            return null;
        }

        for (CorretorModeloEnum x : CorretorModeloEnum.values()) {
            if(id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}
