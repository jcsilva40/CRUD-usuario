package com.stfn2.ggas.domain.dtofilter;

import com.stfn2.ggas.core.abstractClasses.FilterDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrecoMedioPonderadoFilterDTO extends FilterDTO {
	private Integer referencia;
	private LocalDate dataVigencia;
	private Boolean habilitado;
}