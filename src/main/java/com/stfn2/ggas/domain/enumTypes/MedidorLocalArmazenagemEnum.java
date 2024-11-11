package com.stfn2.ggas.domain.enumTypes;

import lombok.Getter;

@Getter
public enum MedidorLocalArmazenagemEnum {

    ALMOXARIFADO(1L, "ALX", false),
    DEPOSITO(2L, "DEP1", true),
    OFICINA(3L, "OF12", true),
    CLIENTE(4L, "CLI", false),
    NAO_INFORMADO(5L, "NF", false);

    private Long id;
    private String abreviado;
    private boolean oficina;

    private MedidorLocalArmazenagemEnum(Long id, String abreviado, Boolean oficina){
        this.id = id;
        this.abreviado = abreviado;
        this.oficina = oficina;
    }

    public static MedidorLocalArmazenagemEnum toEnum(Long id) {
        if(id==null) {
            return null;
        }

        for (MedidorLocalArmazenagemEnum x : MedidorLocalArmazenagemEnum.values()) {
            if(id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}
