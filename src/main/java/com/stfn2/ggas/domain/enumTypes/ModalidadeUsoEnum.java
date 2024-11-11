package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum ModalidadeUsoEnum {

	CALANDRA(449L, "CALANDRA"),
	ESTUFA(452L, "ESTUFA"),
	FORNO_INDUSTRIAL(453L, "FORNO INDUSTRIAL"),
	GERADOR(454L, "GERADOR"),
	GHP(455L, "GHP"),
	INCINERADOR(456L, "INCINERADOR"),
	REATOR(457L, "REATOR"),
	SECADOR(458L, "SECADOR"),
	TURBINA(459L, "TURBINA"),
	OUTRO(460L, "OUTRO"),
	CHILLER(450L, "CHILLER"),
	EMPILHADEIRA(451L, "EMPILHADEIRA"),
	COACAO(215L, "COAÇÃO"),
	AQUECIMENTO_E_COACAO(216L, "AQUECIMENTO E COAÇÃO"),
	AQUECIMENTO(217L, "AQUECIMENTO"),
	CALDEIRA(218L, "CALDEIRA"),
	CENTRAL_DE_AQUECIMENTO(219L, "CENTRAL DE AQUECIMENTO");

	private Long id;
	private String descricao;

	private ModalidadeUsoEnum(Long cod, String descricao) {
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


	public static ModalidadeUsoEnum toEnum(Long id) {
		if(id==null) {
			return null;
		}
		
		for (ModalidadeUsoEnum x : ModalidadeUsoEnum.values()) {
			if(id.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + id);
	}
}
