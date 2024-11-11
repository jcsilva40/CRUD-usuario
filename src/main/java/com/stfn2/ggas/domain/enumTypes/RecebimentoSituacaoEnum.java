package com.stfn2.ggas.domain.enumTypes;

import lombok.Getter;

@Getter
public enum RecebimentoSituacaoEnum {

    CLASSIFICADO(1L),
    DUPLICIDADE(2L),
    DOCTO_INEXISTENTE(3L),
    VALOR_NAO_CONFERE(6L),
    ESTORNADO(7L),
    BAIXA_POR_DACAO(8L),
    NAO_VERIFICADO(9L);

    private final Long id;

    RecebimentoSituacaoEnum(Long id){
        this.id = id;
    }

    public static RecebimentoSituacaoEnum toEnum(Long id) {
        if(id==null) {
            return null;
        }

        for (RecebimentoSituacaoEnum x : RecebimentoSituacaoEnum.values()) {
            if(id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }

}
