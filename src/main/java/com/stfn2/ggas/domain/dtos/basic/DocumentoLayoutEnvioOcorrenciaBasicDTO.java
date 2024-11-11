package com.stfn2.ggas.domain.dtos.basic;
import com.stfn2.ggas.core.interfaces.BaseDTO;

import com.stfn2.ggas.domain.DocumentoLayoutEnvioOcorrencia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoLayoutEnvioOcorrenciaBasicDTO extends BaseDTO {
        private Long id;

    	public DocumentoLayoutEnvioOcorrenciaBasicDTO (DocumentoLayoutEnvioOcorrencia entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}