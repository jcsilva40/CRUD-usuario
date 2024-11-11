package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.ConsumoAnormalidade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsumoAnormalidadeDTO extends BaseDTO {
        private Long id;
	private String descricao;

    	public ConsumoAnormalidadeDTO (ConsumoAnormalidade entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}