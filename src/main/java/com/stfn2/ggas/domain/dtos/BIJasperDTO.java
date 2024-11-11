package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.BIJasper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BIJasperDTO extends BaseDTO {
        private Long id;
	private String descricao;

    	public BIJasperDTO (BIJasper entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}