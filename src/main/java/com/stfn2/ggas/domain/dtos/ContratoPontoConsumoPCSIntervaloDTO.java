package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.ContratoPontoConsumoPCSIntervalo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContratoPontoConsumoPCSIntervaloDTO extends BaseDTO {
        private Long id;

    	public ContratoPontoConsumoPCSIntervaloDTO (ContratoPontoConsumoPCSIntervalo entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}