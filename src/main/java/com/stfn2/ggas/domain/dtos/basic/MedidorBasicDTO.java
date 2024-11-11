package com.stfn2.ggas.domain.dtos.basic;
import com.stfn2.ggas.core.interfaces.BaseDTO;

import com.stfn2.ggas.domain.Medidor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedidorBasicDTO extends BaseDTO {
        private Long id;
		private String descricao;

    	public MedidorBasicDTO (Medidor entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}