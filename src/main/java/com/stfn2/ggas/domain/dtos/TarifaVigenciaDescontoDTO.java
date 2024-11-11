package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.TarifaVigenciaDesconto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TarifaVigenciaDescontoDTO extends BaseDTO {

	private Long id;

	public TarifaVigenciaDescontoDTO (TarifaVigenciaDesconto entity) {
        	super();
    	}

	@Override
	public Long getId() {
		return this.id;
	}
}
