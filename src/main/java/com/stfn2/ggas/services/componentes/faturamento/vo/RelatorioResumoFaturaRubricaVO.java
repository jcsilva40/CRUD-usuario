package com.stfn2.ggas.services.componentes.faturamento.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
public class RelatorioResumoFaturaRubricaVO implements Serializable {
	private static final long serialVersionUID = 5027619732009190427L;

	private Collection<SubRelatorioGrupoFaturamentoVO> listaSubRelatorioGrupoFaturamentoVO;
	private String descricaoGrupoFaturamento;
}
