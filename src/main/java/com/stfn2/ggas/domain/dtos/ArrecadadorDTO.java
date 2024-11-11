package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Arrecadador;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArrecadadorDTO extends BaseDTO {

		private Long id;
		private String codigoAgente;
		private Long bancoId;
		private Long clienteId;
		private LocalDate arrecadadorContratoDataInicioContrato;

    	public ArrecadadorDTO (Arrecadador entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}