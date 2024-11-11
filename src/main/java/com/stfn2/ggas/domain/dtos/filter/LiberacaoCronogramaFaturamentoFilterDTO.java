package com.stfn2.ggas.domain.dtos.filter;

import com.stfn2.ggas.core.abstractClasses.FilterDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LiberacaoCronogramaFaturamentoFilterDTO extends FilterDTO {
  private Integer anoMes;
	private Integer ciclo;
	private Boolean status;
	private Long faturamentoGrupoId = 0L;
	private Boolean verificacao;
	private Long idUser;
	private Long id;
}