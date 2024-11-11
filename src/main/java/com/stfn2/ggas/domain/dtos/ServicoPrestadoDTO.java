package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.ServicoPrestado;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServicoPrestadoDTO extends BaseDTO {

	private Long id;
	public ServicoPrestadoDTO (ServicoPrestado entity) {
        	super();
    	}

	@Override
	public Long getId() {
		return this.id;
	}
}
