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
public class UsuarioPermissoesFilterDTO extends FilterDTO {
	private Long id;
	private Long permissaoId;
	private String permissaoDescricao;
	private Long userId;
	private String userNome;
}