package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.TarifaPontoConsumo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TarifaPontoConsumoDTO extends BaseDTO {

        private Long id;
	
	public TarifaPontoConsumoDTO (TarifaPontoConsumo entity) {
        	super();
    	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}
