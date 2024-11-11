package com.stfn2.ggas.domain.enumTypes;

import lombok.Getter;

@Getter
public enum MedidorProtecaoEnum {

    FIBRA_DE_VIDRO(1L, "FIB"),
    ALUMINIO_COM_EPOXI(2L, "ALU");

    private Long id;
    private String abreviado;

    private MedidorProtecaoEnum(Long id, String abreviado){
        this.id = id;
        this.abreviado = abreviado;
    }

    public static MedidorProtecaoEnum toEnum(Long id) {
        if(id==null) {
            return null;
        }

        for (MedidorProtecaoEnum x : MedidorProtecaoEnum.values()) {
            if(id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}
