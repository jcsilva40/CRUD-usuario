package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Leiturista;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeituristaDTO extends BaseDTO {

    private Long id;
	public LeituristaDTO (Leiturista entity) {
        	super();
    	}

	@Override
	public Long getId() {
		return this.id;
	}
}
