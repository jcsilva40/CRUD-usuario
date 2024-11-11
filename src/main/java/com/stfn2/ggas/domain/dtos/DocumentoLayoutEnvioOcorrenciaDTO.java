package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.DocumentoLayoutEnvioOcorrencia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoLayoutEnvioOcorrenciaDTO extends BaseDTO {
        private Long id;

    	public DocumentoLayoutEnvioOcorrenciaDTO (DocumentoLayoutEnvioOcorrencia entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}