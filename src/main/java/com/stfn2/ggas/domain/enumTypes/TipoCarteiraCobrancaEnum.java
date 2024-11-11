package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TipoCarteiraCobrancaEnum {

	ESCRITURAL(38l, "Escritural"),
	DIRETA(	39l, "Direta"),
	SEM_REGISTRO(	40l, "Sem Registro");

	private Long id;
	private String descricao;

	private TipoCarteiraCobrancaEnum(Long cod, String descricao) {
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


	public static TipoCarteiraCobrancaEnum toEnum(Long id) {
		if(id==null) {
			return null;
		}
		
		for (TipoCarteiraCobrancaEnum x : TipoCarteiraCobrancaEnum.values()) {
			if(id.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + id);
	}
}
