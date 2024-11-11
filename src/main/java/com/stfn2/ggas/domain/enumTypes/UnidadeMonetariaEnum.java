package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum UnidadeMonetariaEnum {

	REAL(62L, "Real"),
	DOLAR(63L, "Dolar");

	private Long id;
	private String descricao;

	private UnidadeMonetariaEnum(Long cod, String descricao) {
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


	public static UnidadeMonetariaEnum toEnum(Long id) {
		if(id==null) {
			return null;
		}
		
		for (UnidadeMonetariaEnum x : UnidadeMonetariaEnum.values()) {
			if(id.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + id);
	}
}
