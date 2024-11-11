package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Arrecadador;
import com.stfn2.ggas.domain.ArrecadadorContrato;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArrecadadorContratoDTO extends BaseDTO {
        private Long id;
		private Arrecadador arrecadador;
		private String numeroContrato;
		private LocalDate dataInicioContrato;

    	public ArrecadadorContratoDTO (ArrecadadorContrato entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}