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
public class SubRelatorioTributosIncluidosVO implements Serializable {
	private static final long serialVersionUID = -6977971285689473512L;

	private String valorBaseCalculoICMS;
	private String valorAliquotaICMS;
	private String valorICMS;
	private String valorBaseCalculoPIS;
	private String valorAliquotaPIS;
	private String valorPIS;
	private String baseCalculoFiscoICMS;
	private String valorRetidoFiscoICMS;
	private String informacoesComplementares;
	private String valorBaseCalculoIPI;
	private String valorAliquotaIPI;
	private String valorIPI;
}
