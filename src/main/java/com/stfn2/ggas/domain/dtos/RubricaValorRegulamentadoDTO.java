package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.RubricaValorRegulamentado;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RubricaValorRegulamentadoDTO extends BaseDTO {

	private Long id;
	public RubricaValorRegulamentadoDTO (RubricaValorRegulamentado entity) {
        	super();
    	}

	@Override
	public Long getId() {
		return this.id;
	}
}
