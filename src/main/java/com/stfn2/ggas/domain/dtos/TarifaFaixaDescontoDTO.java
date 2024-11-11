package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.TarifaFaixaDesconto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TarifaFaixaDescontoDTO extends BaseDTO {

        private Long id;
	
	public TarifaFaixaDescontoDTO (TarifaFaixaDesconto entity) {
        	super();
    	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}
