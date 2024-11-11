package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.RotaCronograma;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RotaCronogramaDTO extends BaseDTO {
        private Long id;
	private String descricao;

    	public RotaCronogramaDTO (RotaCronograma entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}