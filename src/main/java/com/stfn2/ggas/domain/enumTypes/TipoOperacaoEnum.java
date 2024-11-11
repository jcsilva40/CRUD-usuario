package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TipoOperacaoEnum {

	ENTRADA(50L,	"ENTRADA"),
	SAIDA(51L,	"SAIDA");

	private Long id;
	private String descricao;

	private TipoOperacaoEnum(Long id, String descricao) {
		this.id = id;
		this.descricao=descricao;
	}

	@JsonValue
	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}


	public static TipoOperacaoEnum toEnum(Long id) {
		if(id==null) {
			return null;
		}

		for (TipoOperacaoEnum x : TipoOperacaoEnum.values()) {
			if(id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + id);
	}
}
