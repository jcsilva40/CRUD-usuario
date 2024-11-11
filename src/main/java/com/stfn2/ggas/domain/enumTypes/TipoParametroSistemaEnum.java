package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TipoParametroSistemaEnum {

	ESTATICO(1,"Estático"),
	DINAMICA(2,"Entidade dinâmica"),
	NEGOCIO(3,"Negócio");

	private Integer id;
	private String descricao;

	private TipoParametroSistemaEnum(Integer id, String descricao) {
		this.id = id;
		this.descricao=descricao;
	}

	@JsonValue
	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}


	public static TipoParametroSistemaEnum toEnum(Integer id) {
		if(id==null) {
			return null;
		}

		for (TipoParametroSistemaEnum x : TipoParametroSistemaEnum.values()) {
			if(id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + id);
	}
}
