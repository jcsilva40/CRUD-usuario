package com.stfn2.ggas.domain.dtos.basic;
import com.stfn2.ggas.core.interfaces.BaseDTO;

import com.stfn2.ggas.domain.DocumentoLayoutCampo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoLayoutCampoBasicDTO extends BaseDTO {
        private Long id;

    	public DocumentoLayoutCampoBasicDTO (DocumentoLayoutCampo entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}