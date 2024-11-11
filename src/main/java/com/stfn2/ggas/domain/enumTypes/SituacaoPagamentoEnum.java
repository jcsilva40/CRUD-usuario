package com.stfn2.ggas.domain.enumTypes;

import lombok.Getter;

@Getter
public enum SituacaoPagamentoEnum {

	PENDENTE(91L, "Pendente"),
	PARCIALMENTE_QUITADO(92L, "Parcialmente Quitado"),
	QUITADO(111L, "Quitado"),
	CANCELADO(196L, "Cancelado"),
	DEVOLVIDO(474L, "Devolvido");

	private Long id;
	private String descricao;

	private SituacaoPagamentoEnum(Long id, String descricao) {
		this.id = id;
		this.descricao=descricao;
	}

	public static SituacaoPagamentoEnum toEnum(Long id) {
		if(id==null) {
			return null;
		}

		for (SituacaoPagamentoEnum x : SituacaoPagamentoEnum.values()) {
			if(id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + id);
	}
}