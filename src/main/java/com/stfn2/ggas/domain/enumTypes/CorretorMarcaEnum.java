package com.stfn2.ggas.domain.enumTypes;

import lombok.Getter;

@Getter
public enum CorretorMarcaEnum {

    COMPAGAS(5L);

    private Long id;

    private CorretorMarcaEnum(Long id){
        this.id = id;
    }

    public static CorretorMarcaEnum toEnum(Long id) {
        if(id==null) {
            return null;
        }

        for (CorretorMarcaEnum x : CorretorMarcaEnum.values()) {
            if(id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}
