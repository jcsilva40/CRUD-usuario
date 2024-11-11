package com.stfn2.ggas.domain.enumTypes;

import lombok.Getter;

@Getter
public enum MedidorTipoEnum {

    DIAFRAGMA(1L, "DIAFRG"),
    ROTATIVO(2L, "ROTATV"),
    TURBINA(3L, "TURBIN"),
    ULTRASSÔNICO(4L, "ULTRAS"),
    CARRETEL(6L, "CARRET"),
    PLACA_ORIFICIO(7L, "PLACAO"),
    NAO_INFORMADO(8L, "NF");

    private Long id;
    private String abreviado;

    private MedidorTipoEnum(Long id, String abreviado){
        this.id = id;
        this.abreviado = abreviado;
    }

    public static MedidorTipoEnum toEnum(Long id) {
        if(id==null) {
            return null;
        }

        for (MedidorTipoEnum x : MedidorTipoEnum.values()) {
            if(id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }

}
