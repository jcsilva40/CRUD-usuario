package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.TarifaVigenciaFaixa;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TarifaVigenciaFaixaDTO extends BaseDTO {

	private Long id;

	public TarifaVigenciaFaixaDTO (TarifaVigenciaFaixa entity) {
        	super();
    	}

	@Override
	public Long getId() {
		return this.id;
	}
}
