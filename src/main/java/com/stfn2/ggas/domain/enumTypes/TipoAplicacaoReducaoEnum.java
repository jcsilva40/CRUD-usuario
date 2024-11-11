package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TipoAplicacaoReducaoEnum {

	PERCENTUAL(564L, "Perc"),
	FATOR(565L, "Fatr");

	private Long id;
	private String abreviado;

	private TipoAplicacaoReducaoEnum(Long cod, String abreviado) {
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


	public static TipoAplicacaoReducaoEnum toEnum(Long id) {
		if(id==null) {
			return null;
		}
		
		for (TipoAplicacaoReducaoEnum x : TipoAplicacaoReducaoEnum.values()) {
			if(id.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + id);
	}
}
