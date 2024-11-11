package com.stfn2.ggas.domain.enumTypes;


import lombok.Getter;

@Getter
public enum CreditoDebitoSituacaoEnum {

    NORMAL(1L, "Normal", "NOR"),
    RETIFICADA(2L, "Retificada", "RET"),
    INCLUIDA(3L, "Incluida", "INC"),
    PARCELADA(4L, "Parcelada", "PAR"),
    CANCELADA_POR_REFATURAMENTO(5L, "Cancelada por refaturamento", "CRF"),
    NFE_A_GERAR(6L, "NFE a gerar", "NFEP"),
    CANCELADA(9L, "Cancelada", "CAN");


    private Long id;
    private String descricao;
    private String descAbreviada;

    private CreditoDebitoSituacaoEnum(Long cod, String descricao, String abreviada) {
        this.id = cod;
        this.descricao=descricao;
        this.descAbreviada = abreviada;
    }

    public static CreditoDebitoSituacaoEnum toEnum(Long id) {
        if(id==null) {
            return null;
        }

        for (CreditoDebitoSituacaoEnum x : CreditoDebitoSituacaoEnum.values()) {
            if(id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }

}
