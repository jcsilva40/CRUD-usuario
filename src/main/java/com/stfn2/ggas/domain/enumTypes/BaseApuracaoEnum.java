package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum BaseApuracaoEnum {

	DIARIA(159L, "Diária"),
	MEDIA_DIARIA(158L, "Média Diária"),
	PERIODICO(157L, "Periódico");

	private Long id;
	private String descricao;

	private BaseApuracaoEnum(Long cod, String descricao) {
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


	public static BaseApuracaoEnum toEnum(Long id) {
		if(id==null) {
			return null;
		}
		
		for (BaseApuracaoEnum x : BaseApuracaoEnum.values()) {
			if(id.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + id);
	}
}
