package com.stfn2.ggas.domain.dtos.basic;
import com.stfn2.ggas.core.interfaces.BaseDTO;

import com.stfn2.ggas.domain.DocumentoLayoutRegistro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoLayoutRegistroBasicDTO extends BaseDTO {
        private Long id;

    	public DocumentoLayoutRegistroBasicDTO (DocumentoLayoutRegistro entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}