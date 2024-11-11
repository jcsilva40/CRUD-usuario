package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum CodigoCarteiraCobrancaEnum {

	ELETRONICA_COM_REGISTRO(	887l, "Eletrônica com Registro"),
	PENHOR_ELETRÔNICA(	888l, "Penhor Eletrônica"),
	RAPIDA_COM_REGISTRO(	889l, "Rápida com Registro"),
	PENHOR_RAPIDA(	890l, "Penhora Rápida"),
	DESCONTO_ELETRONICO(	891l, "Desconto Eletronico"),
	I(41l, "I"),
	E(42l, "E"),
	U(43l, "U");

	private Long id;
	private String descricao;

	private CodigoCarteiraCobrancaEnum(Long cod, String descricao) {
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


	public static CodigoCarteiraCobrancaEnum toEnum(Long id) {
		if(id==null) {
			return null;
		}
		
		for (CodigoCarteiraCobrancaEnum x : CodigoCarteiraCobrancaEnum.values()) {
			if(id.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + id);
	}
}
