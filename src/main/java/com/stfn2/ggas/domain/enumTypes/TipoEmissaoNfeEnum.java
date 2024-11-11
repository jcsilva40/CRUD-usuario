package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TipoEmissaoNfeEnum {

	NORMAL(403L,"NORMAL"),
	CONTIGENCIA(404L,"CONTINGÊNCIA");

	private Long id;
	private String descricao;

	private TipoEmissaoNfeEnum(Long id, String descricao) {
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


	public static TipoEmissaoNfeEnum toEnum(Long id) {
		if(id==null) {
			return null;
		}

		for (TipoEmissaoNfeEnum x : TipoEmissaoNfeEnum.values()) {
			if(id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + id);
	}
}
