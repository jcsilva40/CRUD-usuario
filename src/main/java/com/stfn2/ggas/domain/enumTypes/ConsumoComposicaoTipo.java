package com.stfn2.ggas.domain.enumTypes;

import lombok.Getter;

@Getter
public enum ConsumoComposicaoTipo {

    MEDIDOR_ATUAL(1L, true),
    MEDIDOR_ANTERIOR_MAIS_ATUAL(2L, false),
    MEDIDOR_ATUAL_AJUST(3L, false);

    private Long id;
    private boolean padrao;

    private ConsumoComposicaoTipo(Long id, boolean padrao){
        this.id = id;
        this.padrao = padrao;
    }

    public static ConsumoComposicaoTipo toEnum(Long id){
        if(id==null) {
            return null;
        }

        for(ConsumoComposicaoTipo x : ConsumoComposicaoTipo.values()){
            if(id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}
