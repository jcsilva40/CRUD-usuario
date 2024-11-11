package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TipoCalculoEnum {

	FAIXA(161L, "Faixa"),
	CASCATA(138L, "Cascata");

	private Long id;
	private String descricao;

	private TipoCalculoEnum(Long cod, String descricao) {
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


	public static TipoCalculoEnum toEnum(Long id) {
		if(id==null) {
			return null;
		}
		
		for (TipoCalculoEnum x : TipoCalculoEnum.values()) {
			if(id.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + id);
	}
}
