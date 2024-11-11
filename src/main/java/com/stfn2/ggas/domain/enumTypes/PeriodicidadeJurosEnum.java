package com.stfn2.ggas.domain.enumTypes;

import lombok.Getter;

@Getter
public enum PeriodicidadeJurosEnum {

    ANUAL(44L, "ANUAL"),
    MENSAL(84L, "MENSAL"),
    DIARIO(85L, "DIÁRIO");

    private Long id;
    private String descricao;

    private PeriodicidadeJurosEnum(Long id, String descricao) {
        this.id = id;
        this.descricao=descricao;
    }


    public static PeriodicidadeJurosEnum toEnum(Long id) {
        if(id==null) {
            return null;
        }

        for (PeriodicidadeJurosEnum x : PeriodicidadeJurosEnum.values()) {
            if(id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}
