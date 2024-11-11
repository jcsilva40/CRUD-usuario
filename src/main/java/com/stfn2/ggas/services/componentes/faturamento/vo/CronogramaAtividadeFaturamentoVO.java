package com.stfn2.ggas.services.componentes.faturamento.vo;

import com.stfn2.ggas.domain.AtividadeSistema;
import com.stfn2.ggas.domain.RotaCronograma;
import com.stfn2.ggas.tools.ToolDate;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CronogramaAtividadeFaturamentoVO implements Serializable {
	@Serial
	private static final long serialVersionUID = -5796741790746846390L;

	private Long chavePrimaria;
	private int versao;
	private AtividadeSistema atividadeSistema;
	private LocalDate dataInicio;
	private LocalDate dataLimite;
	private LocalDate dataPrevista;
	private Integer duracao;
	private Integer intervalo;
	private Boolean indicadorMarcado;
	private List<RotaCronograma> listaCronogramaRota = new ArrayList<>();
	private String dataInicialAtividade;
	private String dataFinalAtividade;

	public String getDataLimiteFormatada() {
		String dataFormatada = "";
		if(dataLimite != null) {
			dataFormatada = ToolDate.converterDataParaString(dataLimite, false);
		}
		return dataFormatada;
	}
	public String getDataPrevistaFormatada() {
		String dataFormatada = "";
		if(dataPrevista != null) {
			dataFormatada = ToolDate.converterDataParaString(dataPrevista, false);
		}
		return dataFormatada;
	}

	public Integer getIntervaloDias() {
		Integer retorno = 0;
		if(dataLimite != null && dataInicio != null) {
			retorno = ToolDate.diferencaDiasEntreDatas(dataInicio, dataLimite);
		}
		return retorno;
	}
}
