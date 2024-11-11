package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TipoDocumentoImpressoEnum {

	RELATORIO1(408L, "RELATORIO"),
	RELATORIO2(495L, "Relatorio"),
	AVISO_CORTE(502L, "Aviso de Corte"),
	NOTIFICACAO_CORTE(503L, "Notificação de Corte"),
	RELATORIO_NOTA_FISCAL_FATURA(504L, "RELATORIO NOTA FISCAL FATURA"),
	RELATORIO_VERSO_NOTA_FISCAL_FATURA(507L, "RELATORIO VERSO NOTA FISCAL FATURA"),
	RELATORIO_COMPLEMENTO_NOTA_FISCAL_FATURA(508L, "RELATORIO COMPLEMENTO NOTA FISCAL FATURA"),
	RELATORIO_NOTA_DEBITO(561L, "RELATORIO NOTA DE DEBITO"),
	RELATORIO_SERVICO(911L, "	Autorização de Serviço");

	private Long id;
	private String descricao;

	private TipoDocumentoImpressoEnum(Long id, String descricao) {
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


	public static TipoDocumentoImpressoEnum toEnum(Long id) {
		if(id==null) {
			return null;
		}
		
		for (TipoDocumentoImpressoEnum x : TipoDocumentoImpressoEnum.values()) {
			if(id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + id);
	}
}
