package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.LeituraTipo;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeituraTipoDTO extends BaseDTO {
	private Long id;

	public LeituraTipoDTO (LeituraTipo entity) {
        	super();
    	}

	@Override
	public Long getId() {
		return this.id;
	}
}
