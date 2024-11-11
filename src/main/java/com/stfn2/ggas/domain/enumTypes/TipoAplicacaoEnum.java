package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TipoAplicacaoEnum {

	PERCENTUAL(562L, "Perc"),
	VALOR(563L, "Valr");

	private Long id;
	private String abreviado;

	private TipoAplicacaoEnum(Long cod, String abreviado) {
		this.id = cod;
		this.abreviado=abreviado;
	}

	@JsonValue
	public Long getCod() {
		return id;
	}

	public String getAbreviado() {
		return abreviado;
	}


	public static TipoAplicacaoEnum toEnum(Long id) {
		if(id==null) {
			return null;
		}
		
		for (TipoAplicacaoEnum x : TipoAplicacaoEnum.values()) {
			if(id.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + id);
	}
}
