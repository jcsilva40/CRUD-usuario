package com.stfn2.ggas.domain.dtos.basic;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.DocumentoFiscal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoFiscalBasicDTO extends BaseDTO {
        private Long id;
	private String descricao;

    	public DocumentoFiscalBasicDTO (DocumentoFiscal entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}