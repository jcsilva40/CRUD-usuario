package com.stfn2.ggas.services.componentes.faturamento.vo;

import com.stfn2.ggas.domain.FaturamentoAtividadeCronograma;
import com.stfn2.ggas.domain.Rota;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class CronogramaAtividadeRotaVO implements Serializable {

	@Serial
	private static final long serialVersionUID = 7082315988723909922L;

	private Rota rota;
	private String dataPrevista;
	private String dataCiclo;
	private Integer diasLeitura;
	private Integer quantidadeLeitura;
	private String nomeleiturista;
	private boolean emAlerta = false;
	private List<Rota> rotasEmAlerta = new ArrayList<>();
	private FaturamentoAtividadeCronograma cronogramaAtividadeFaturamento;
	private String dataInicio;
	private String dataRealizada;

	public String getEmAlertaFormatado() {
		String emAlertaFormatado = "Não";
		if(isEmAlerta()) {
			emAlertaFormatado = "Sim";
		}
		return emAlertaFormatado;
	}

	public String getRotasEmAlertaFormatado() {
		StringBuilder sb = new StringBuilder("Rota(s):[");
		if(this.rotasEmAlerta != null) {
			for (Rota rotaAlerta : this.rotasEmAlerta) {
				sb.append("(").append(rotaAlerta.getDescricao()).append(")");
			}
		}
		sb.append("]");
		return sb.toString();
	}
}
