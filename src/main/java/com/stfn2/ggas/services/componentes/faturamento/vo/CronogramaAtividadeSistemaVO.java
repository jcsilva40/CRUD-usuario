package com.stfn2.ggas.services.componentes.faturamento.vo;

import com.stfn2.ggas.domain.FaturamentoCronograma;
import com.stfn2.ggas.tools.ToolDate;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
public class CronogramaAtividadeSistemaVO implements Serializable {
	@Serial
	private static final long serialVersionUID = -2143163486827928916L;

	private Collection<CronogramaAtividadeFaturamentoVO> listaCronogramaAtividadeFaturamentoVO;
	private FaturamentoCronograma cronogramaFaturamento;
	private Integer anoMes;
	private Integer ciclo;

	public String getMesAnoCicloFormatado() {

		String mesAnoCiclo = "";

		if(anoMes != 0 && String.valueOf(anoMes).length() == 6 && ciclo > 0) {
			mesAnoCiclo = String.valueOf(ToolDate.converterAnoMesEmMesAno(anoMes.toString()));
			mesAnoCiclo = mesAnoCiclo.substring(0, 2) + "/" + mesAnoCiclo.substring(2, 6);
			mesAnoCiclo = mesAnoCiclo + " - " + ciclo;
		}

		return mesAnoCiclo;
	}

	public String getMesAnoFormatado() {

		String mesAno = "";

		if(anoMes != 0 && String.valueOf(anoMes).length() == 6 && ciclo > 0) {
			mesAno = String.valueOf(ToolDate.converterAnoMesEmMesAno(anoMes.toString()));
			mesAno = mesAno.substring(0, 2) + "/" + mesAno.substring(2, 6);
		}

		return mesAno;
	}
}
