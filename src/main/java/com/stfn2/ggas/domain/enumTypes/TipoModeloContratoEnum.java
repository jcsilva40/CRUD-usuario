package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TipoModeloContratoEnum {

	COMPLEMENTAR(517L, "Contrato Complementar"),
	PRINCIPAL(516L, "Contrato Principal");

	private Long id;
	private String abreviado;

	private TipoModeloContratoEnum(Long cod, String abreviado) {
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


	public static TipoModeloContratoEnum toEnum(Long id) {
		if(id==null) {
			return null;
		}
		
		for (TipoModeloContratoEnum x : TipoModeloContratoEnum.values()) {
			if(id.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + id);
	}
}
