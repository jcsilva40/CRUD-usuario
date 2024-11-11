package com.stfn2.ggas.domain.dtos.basic;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.LogCampanhaDesconto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogCampanhaDescontoBasicDTO extends BaseDTO {
        private Long id;
	private String descricao;

    	public LogCampanhaDescontoBasicDTO (LogCampanhaDesconto entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}