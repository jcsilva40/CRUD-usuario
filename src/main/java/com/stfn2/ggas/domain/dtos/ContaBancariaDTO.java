package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.ContaBancaria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContaBancariaDTO extends BaseDTO {
        private Long id;

    	public ContaBancariaDTO (ContaBancaria entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}