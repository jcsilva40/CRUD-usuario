package com.stfn2.ggas.services.componentes.faturamento.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoPagamentoRelatorioFaturaVersoVO {

	private String mesAno;
	private String vencimento;
	private String valor;
	private String pagamento;
}
