package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum PontoConsumoSituacaoEnum {

	AGUARDANDO_ATIVACAO(1L, "Aguardando Ativação"),
	ATIVO(2L, "Ativo"),
	BLOQUEADO(3L, "Bloqueado"),
	BLOQUEADO_TECNICAMENTE(4L, "Bloqueado Tecnicamento"),
	SUPRIMIDO_DEFINITIVO(5L, "Suprimido em Definitivo"),
	SUPRIMIDO_PARCIAL(6L, "Suprimido Parcial"),
	SUPRIMIDO_TOTAL(7L, "Suprimido Total"),
	SUSPENSO	(8L, "Suspenso");

	private Long id;
	private String descricao;

	PontoConsumoSituacaoEnum(Long cod, String descricao) {
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


	public static PontoConsumoSituacaoEnum toEnum(Long id) {
		if(id==null) {
			return null;
		}
		
		for (PontoConsumoSituacaoEnum x : PontoConsumoSituacaoEnum.values()) {
			if(id.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + id);
	}
}
