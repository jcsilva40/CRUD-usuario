package com.stfn2.ggas.domain.dtos.basic;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.ConstanteSistema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConstanteSistemaBasicDTO extends BaseDTO {
        private Long id;
	private String descricao;

    	public ConstanteSistemaBasicDTO (ConstanteSistema entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}