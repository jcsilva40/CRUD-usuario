package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.UnidadeOrganizacional;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnidadeOrganizacionalDTO extends BaseDTO {

	private Long id;

	public UnidadeOrganizacionalDTO (UnidadeOrganizacional entity) {
        	super();
    	}

	@Override
	public Long getId() {
		return this.id;
	}
}
