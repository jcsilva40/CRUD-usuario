package com.stfn2.ggas.domain.enumTypes;

import lombok.Getter;

@Getter
public enum StatusFaturaEnum {

	VALIDACAO_PENDENTE(915L,"Valicação Pendente"),
	VALIDADA_BLOQUEADA(916L,"Validada Bloqueada"),
	VALIDADA_LIBERADA(917L,"Validada Liberada"),
	AGUARDANDO_RETORNO_SEFAZ(918L,"Aguardando Retorno SEFAZ"),
	PROCESSO_DE_CANCELAMENTO(919L,"Processo de Cancelamento"),
	RETORNO_SEFAZ_AUTORIZADA(920L,"Retorno Sefaz Autorizada"),
	RETORNO_SEFAZ_NEGADA(921L,"Retorno Sefaz Negada"),
	RETORNO_SEFAZ_CANCELADA(922L,"Retorno Sefaz Cancelada"),
	LIBERADA_ENVIO(923L,"Liberada Envio"),
	ENVIADA_CLIENTE(924L,"Enviada Cliente");

	private Long id;
	private String descricao;

	private StatusFaturaEnum(Long id, String descricao) {
		this.id = id;
		this.descricao=descricao;
	}

	public static StatusFaturaEnum toEnum(Long id) {
		if(id==null) {
			return null;
		}
		
		for (StatusFaturaEnum x : StatusFaturaEnum.values()) {
			if(id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + id);
	}
}
