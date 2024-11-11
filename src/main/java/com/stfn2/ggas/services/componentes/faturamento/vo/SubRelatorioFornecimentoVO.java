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
public class SubRelatorioFornecimentoVO implements Serializable {
	private static final long serialVersionUID = 4321246999383000363L;

	private String medidor;
	private String dataAtual;
	private String leituraAtual;
	private String dataAnterior;
	private String leituraAnterior;
	private String consumoNaoCorrigido = "0";
	private String fatorPTZ;
	private String fatorCorrecao;
	private String fatorPCS;
	private String consumoCorrigido = "0";
	private String totalFornecimento;
	private String quantidadeDias;
	private String tipoConsumo;
	private String consumoFaturado;
	private String pressaoAtual;
}
