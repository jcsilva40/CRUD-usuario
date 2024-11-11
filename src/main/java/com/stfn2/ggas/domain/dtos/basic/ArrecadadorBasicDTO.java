package com.stfn2.ggas.domain.dtos.basic;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Arrecadador;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArrecadadorBasicDTO extends BaseDTO {

        private Long id;
		private String nomeBanco;
		private String codigoAgente;
		private String nomeCliente;

    	public ArrecadadorBasicDTO (Arrecadador entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}