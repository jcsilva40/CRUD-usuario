package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.TarifaVigenciaTributo;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TarifaVigenciaTributoDTO extends BaseDTO {

	private Long id;

	public TarifaVigenciaTributoDTO (TarifaVigenciaTributo entity) {
        	super();
    	}

	@Override
	public Long getId() {
		return this.id;
	}
}
