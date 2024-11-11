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
public class SubRelatorioPontoConsumoVO implements Serializable {
	private static final long serialVersionUID = 623828927223976597L;

	private String segmento;
	private String tarifa;
	private String tipoConsumo;
	private String dataProximaLeitura;
	private String descricaoPontoConsumo;
	private String endereco;
	private String numeroDiasPeriodo;
	private String rotaLeitura;
	private String medidor;

}
