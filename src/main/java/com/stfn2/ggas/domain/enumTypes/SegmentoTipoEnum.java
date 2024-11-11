package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum SegmentoTipoEnum {
	
	PARTICULAR(1L, "PARTICULAR", "PAR"),
	PUBLICO(2L, "PUBLICO", "PUB");
	
	private Long id;
        private String descricao;
	private String abreviado;
	
	private SegmentoTipoEnum(Long cod, String descricao, String abreviado) {
            this.id = cod;
            this.descricao = descricao;
            this.abreviado=abreviado;
	}

	@JsonValue
	public Long getCod() {
		return id;
	}

	public String getAbreviado() {
		return abreviado;
	}


	public static SegmentoTipoEnum toEnum(Long id) {
		if(id==null) {
			return null;
		}
		
		for (SegmentoTipoEnum x : SegmentoTipoEnum.values()) {
			if(id.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + id);
	}
}
