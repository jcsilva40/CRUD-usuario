package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TipoFaturamentoEnum {

	PRODUTO(15L,	"PRODUTO"),
	SERVICO(16L,	"SERVIÇO");

	private Long id;
	private String descricao;

	private TipoFaturamentoEnum(Long id, String descricao) {
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


	public static TipoFaturamentoEnum toEnum(Long id) {
		if(id==null) {
			return null;
		}
		
		for (TipoFaturamentoEnum x : TipoFaturamentoEnum.values()) {
			if(id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + id);
	}
}
