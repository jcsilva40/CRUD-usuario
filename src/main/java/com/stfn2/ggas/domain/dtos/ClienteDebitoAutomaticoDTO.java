package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.ClienteDebitoAutomatico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDebitoAutomaticoDTO extends BaseDTO {
        private Long id;
	private String descricao;

    	public ClienteDebitoAutomaticoDTO (ClienteDebitoAutomatico entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}