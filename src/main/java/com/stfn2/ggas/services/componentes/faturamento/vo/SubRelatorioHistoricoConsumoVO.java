package com.stfn2.ggas.services.componentes.faturamento.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubRelatorioHistoricoConsumoVO implements Serializable {
	private static final long serialVersionUID = -5870266402724999178L;

	private String notaFiscalFatura;
	private String vencimento;
	private String tipo;
	private BigDecimal volumeFaturado;
	private String volumeMedido;
	private String valor;
	private String pagamento;
	private BigDecimal valorFaturado;
	private BigDecimal valorMedido;
  private String mesAno;
	private String dataLeitura;
}
