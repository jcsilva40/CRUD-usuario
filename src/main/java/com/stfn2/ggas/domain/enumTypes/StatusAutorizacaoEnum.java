package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum StatusAutorizacaoEnum {

	AUTORIZADO(244L, "Autorizado"),
	NEGADO(243L, "Negado"),
	AGUARDANDO_AUTORIZACAO(242L, "Aguardando Autorização");

	private Long id;
	private String descricao;

	private StatusAutorizacaoEnum(Long cod, String descricao) {
		this.id = cod;
		this.descricao=descricao;
	}

	@JsonValue
	public Long getCod() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}


	public static StatusAutorizacaoEnum toEnum(Long id) {
		if(id==null) {
			return null;
		}
		
		for (StatusAutorizacaoEnum x : StatusAutorizacaoEnum.values()) {
			if(id.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + id);
	}
}
