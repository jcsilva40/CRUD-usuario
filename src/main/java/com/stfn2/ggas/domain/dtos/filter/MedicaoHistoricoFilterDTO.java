package com.stfn2.ggas.domain.dtos.filter;

import com.stfn2.ggas.core.abstractClasses.FilterDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicaoHistoricoFilterDTO extends FilterDTO {
	private Long pontoConsumoId;
	private Long medidorInstalacaoId;
	private String pontoConsumoDescricao;
	private Long anoMes;
	private Long ciclo;
}