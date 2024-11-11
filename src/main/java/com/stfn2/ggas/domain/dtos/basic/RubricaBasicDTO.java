package com.stfn2.ggas.domain.dtos.basic;
import com.stfn2.ggas.core.interfaces.BaseDTO;

import com.stfn2.ggas.domain.Rubrica;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RubricaBasicDTO extends BaseDTO {
        private Long id;
		private Boolean habilitado;
		private String descricao;
		private String descricaoImpressao;
		private String financiamentoTipoDescricao;
		private Long financiamentoTipoId;


	public RubricaBasicDTO (Rubrica entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}