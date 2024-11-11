package com.stfn2.ggas.domain.enumTypes;

import lombok.Getter;

@Getter
public enum MedidorMotivoMovimentacaoEnum {

    MEDIDOR_DANIFICADO(1L, "DANIF"),
    ADEQ_FORNECIMENTO(2L, "ADEQ"),
    ACERTO_NUM_CONTRATO(3L, "ACERT"),
    ERRO_CADASTRAMENTO(4L, "ERRCAD"),
    AFERICAO(5L, "AFER"),
    RET_MEDIDOR_AFERIDO(6L, "RETAFR"),
    CANCEL_CONTR_FORNEC(7L, "CANCEL"),
    MUDANCA_ENDERECO(8L, "MUDEND"),
    ADEQ_MEDIDOR_VAZAO(9L, "ADQVAZ");

    private Long id;
    private String abreviado;

    private MedidorMotivoMovimentacaoEnum(Long id, String abreviado){
        this.id = id;
        this.abreviado = abreviado;

    }

    public static MedidorMotivoMovimentacaoEnum toEnum(Long id) {
        if(id==null) {
            return null;
        }

        for (MedidorMotivoMovimentacaoEnum x : MedidorMotivoMovimentacaoEnum.values()) {
            if(id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}
