package com.stfn2.ggas.domain.dtos.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stfn2.ggas.core.abstractClasses.FilterDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImovelFilterDTO extends FilterDTO {
	private Boolean habilitado;
	private Integer nip;
	private String zonaBloqueio;
	private Long cil;
}