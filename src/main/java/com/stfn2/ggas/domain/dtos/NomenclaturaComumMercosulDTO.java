package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.NomenclaturaComumMercosul;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NomenclaturaComumMercosulDTO extends BaseDTO {

	private Long id;

	public NomenclaturaComumMercosulDTO (NomenclaturaComumMercosul entity) {
        	super();
    	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}
