package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.UnidadeTipo;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnidadeTipoDTO extends BaseDTO {
	private Long id;
	public UnidadeTipoDTO (UnidadeTipo entity) {
        	super();
    	}

	@Override
	public Long getId() {
		return this.id;
	}
}
