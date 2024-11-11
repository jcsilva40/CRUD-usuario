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
public class SubRelatorioFaturasVencidasVO implements Serializable {
	private static final long serialVersionUID = 7102817486608672689L;

	private String mesAno;
	private String vencimento;
	private String valor;
	private String dataCorte;
	private String revisao;
	private String notaFiscalFatura;
	private String tipo;
}
