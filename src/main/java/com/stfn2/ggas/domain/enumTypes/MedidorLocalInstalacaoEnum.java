package com.stfn2.ggas.domain.enumTypes;

import lombok.Getter;

@Getter
public enum MedidorLocalInstalacaoEnum {

    JARDIM(1L, "JAR"),
    QUINTAL(2L, "QUI"),
    CALCADA(3L, "CAL"),
    LATERAL_ESQUERDA(4L, "ESQ"),
    LATERAL_DIREITA(5L, "DIR"),
    INTERIOR_DO_IMOVEL(6L, "INT"),
    HALL_DO_EDIFICIO(7L, "HAL"),
    MURO_DO_IMOVEL(8L, "MUR");

    private Long id;
    private String abreviado;

    private MedidorLocalInstalacaoEnum(Long id, String abreviado){

        this.id = id;
        this.abreviado = abreviado;
    }

    public static MedidorLocalInstalacaoEnum toEnum(Long id) {
        if(id==null) {
            return null;
        }

        for (MedidorLocalInstalacaoEnum x : MedidorLocalInstalacaoEnum.values()) {
            if(id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }

}
