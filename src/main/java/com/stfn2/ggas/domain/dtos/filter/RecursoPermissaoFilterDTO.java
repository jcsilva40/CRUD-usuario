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
public class RecursoPermissaoFilterDTO extends FilterDTO {
	private Boolean habilitado;
	private Long recursoId;
	private String recursoUrl;
	private String recursoVerboHttp;
	private Long permissaoId;
	private String permissaoDescricao;
}