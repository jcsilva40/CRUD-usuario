package com.stfn2.ggas.domain.enumTypes;

import lombok.Getter;

@Getter
public enum ProtocoloComunicacaoDado {

    MODBUS(1L, "MODBUS");

    private Long id;
    private String abreviado;

    private ProtocoloComunicacaoDado(Long id, String abreviado){
        this.id = id;
        this.abreviado = abreviado;

    }

    public static ProtocoloComunicacaoDado toEnum(Long id) {
        if(id==null) {
            return null;
        }

        for (ProtocoloComunicacaoDado x : ProtocoloComunicacaoDado.values()) {
            if(id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}
