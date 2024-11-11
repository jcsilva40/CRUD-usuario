package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum StatusNfeEnum {

	NAO_PROCESSADA(139L,	"NÃO PROCESSADA"),
	AGUARDANDO_ENVIO(140L,	"AGUARDANDO ENVIO"),
	AGUARDANDO_RETORNO(141L,	"AGUARDANDO RETORNO"),
	AUTORIZADA(142L,	"AUTORIZADA"),
	REJEITADA(143L,	"REJEITADA"),
	DENEGADA(144L,	"DENEGADA"),
	CANCELADA(145L,	"CANCELADA"),
	ERRO_DE_VALIDACAO(146L,	"ERRO DE VALIDAÇÃO"),
	ERRO_NA_MONTAGEM_DO_XML(147L,	"ERRO NA MONTAGEM DO XML"),
	ERRO_NO_ENVIO_PARA_A_SEFAZ(148L,	"ERRO NO ENVIO PARA A SEFAZ"),
	ERRO_NA_BUSCA_DO_RETORNO_DA_SEFAZ(149L,	"ERRO NA BUSCA DO RETORNO DA SEFAZ"),
	CONTINGENCIA_NO_ENVIO_PARA_SEFAZ(150L,	"CONTINGÊNCIA NO ENVIO PARA SEFAZ"),
	CONTINGENCIA_NO_RETORNO_DO_ENVIO_PARA_A_SEFAZ(151L,	"CONTINGÊNCIA NO RETORNO DO ENVIO PARA A SEFAZ"),
	ERRO_GERAL(152L,	"ERRO GERAL"),
	AGUARDANDO_ENVIO_DPEC(181L,	"AGUARDANDO ENVIO DPEC"),
	AUTORIZADO_DPEC(182L,	"AUTORIZADO DPEC"),
	FALHA_ENVIO_DPEC(183L,	"FALHA ENVIO DPEC"),
	ERRO_DPEC(184L,	"ERRO DPEC");

	private Long id;
	private String descricao;

	private StatusNfeEnum(Long id, String descricao) {
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


	public static StatusNfeEnum toEnum(Long id) {
		if(id==null) {
			return null;
		}

		for (StatusNfeEnum x : StatusNfeEnum.values()) {
			if(id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + id);
	}
}