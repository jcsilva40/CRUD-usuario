package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TipoConvenioBancarioEnum {

	ARRECADACAO(575l, "Arrecadação", ""),
	BOLETO(576l, "Boleto Bancário", ""),
	DEBITO_AUTOMATICO(577l, "Débito Automático", ""),
        NENHUM(886L, "Nenhum", "");

	private final Long id;
	private final String descricao;
        private final String abreviado;

	private TipoConvenioBancarioEnum(Long cod, String descricao, String abreviado) {
            this.id = cod;
            this.descricao = descricao;
            this.abreviado = abreviado;
	}

	@JsonValue
	public Long getCod() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoConvenioBancarioEnum toEnum(Long id) {
            if(id==null) {
                    return null;
            }

            for (TipoConvenioBancarioEnum x : TipoConvenioBancarioEnum.values()) {
                    if(id.equals(x.getCod())) {
                            return x;
                    }
            }
            throw new IllegalArgumentException("Id inválido: " + id);
	}
}
