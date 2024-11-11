package com.stfn2.ggas.services.componentes.faturamento.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubRelatorioProdutosVO implements Serializable {
	private static final long serialVersionUID = 1368239045186791222L;

	private String descricao;
	private String volumeFaturado;
	private String valorUnitario;
	private String valorTotal;
	private String unidade;
	private String cfop;
	private String dataInicio;
	private String dataFinal;
	private String quantidadeDias;
	private boolean indicadorCredito;
	private String descricaoTarifa;
	private String codigoProduto;
	private String desconto;
}
