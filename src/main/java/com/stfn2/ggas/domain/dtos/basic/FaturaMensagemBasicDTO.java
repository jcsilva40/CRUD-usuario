package com.stfn2.ggas.domain.dtos.basic;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.FaturaMensagem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FaturaMensagemBasicDTO extends BaseDTO {
        private Long id;
	private String descricao;

    	public FaturaMensagemBasicDTO (FaturaMensagem entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}