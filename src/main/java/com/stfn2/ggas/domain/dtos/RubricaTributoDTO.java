package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.RubricaTributo;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RubricaTributoDTO extends BaseDTO {
	private Long id;
	public RubricaTributoDTO (RubricaTributo entity) {
        	super();
    	}

	@Override
	public Long getId() {
		return this.id;
	}
}
