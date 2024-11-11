package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Unidade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnidadeDTO extends BaseDTO {
        private Long id;
        
        private String descricao;
        private String abreviado;
        Boolean padrao;

    	public UnidadeDTO (Unidade entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}