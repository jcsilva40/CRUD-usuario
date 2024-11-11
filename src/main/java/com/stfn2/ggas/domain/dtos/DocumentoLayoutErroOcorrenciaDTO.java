package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.DocumentoLayoutErroOcorrencia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoLayoutErroOcorrenciaDTO extends BaseDTO {
        private Long id;

    	public DocumentoLayoutErroOcorrenciaDTO (DocumentoLayoutErroOcorrencia entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}