package com.stfn2.ggas.domain.dtos.basic;
import com.stfn2.ggas.core.interfaces.BaseDTO;

import com.stfn2.ggas.domain.RubricaTributo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RubricaTributoBasicDTO extends BaseDTO {
        private Long id;

    	public RubricaTributoBasicDTO (RubricaTributo entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}