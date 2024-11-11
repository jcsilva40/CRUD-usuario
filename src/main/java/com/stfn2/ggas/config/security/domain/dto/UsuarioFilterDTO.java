package com.stfn2.ggas.config.security.domain.dto;

import com.stfn2.ggas.core.abstractClasses.FilterDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioFilterDTO extends FilterDTO {
	private String userName;
	private String nome;
	private String email;
	private Boolean habilitado;
}