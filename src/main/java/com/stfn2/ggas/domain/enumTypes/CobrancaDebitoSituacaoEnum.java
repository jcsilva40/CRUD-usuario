package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum CobrancaDebitoSituacaoEnum {

    PENDENTE(1L, "Pendente"),
    PAGO(2L, "Pago"),
    PARCELADO(3L, "Parcelado"),
    CANCELADO(4L, "Cancelado"),
    SEM_DEBITOS(5L, "Sem Débitos"),
    EXECUTADO(6L, "Executado");

    private final Long id;
    private String descricao;

    CobrancaDebitoSituacaoEnum (Long id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }

    @JsonValue
    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public static CobrancaDebitoSituacaoEnum toEnum(Long id) {
        if(id==null) {
            return null;
        }

        for (CobrancaDebitoSituacaoEnum x : CobrancaDebitoSituacaoEnum.values()) {
            if(id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}
