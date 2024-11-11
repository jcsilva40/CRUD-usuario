package com.stfn2.ggas.domain.dtos.basic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.stfn2.ggas.core.interfaces.BaseDTO;

import com.stfn2.ggas.domain.Banco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BancoBasicDTO extends BaseDTO {
	private Long id;

	private String nome;

	private String codigoBanco;

	private String abreviado;

    	public BancoBasicDTO (Banco entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}