package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.TemperaturaTrabalhoFaixa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TemperaturaTrabalhoFaixaDTO extends BaseDTO {
        private Long id;

    	public TemperaturaTrabalhoFaixaDTO (TemperaturaTrabalhoFaixa entity) {super(); 	}

}