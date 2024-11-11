package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.PontoConsumoSituacao;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PontoConsumoSituacaoDTO extends BaseDTO {

    private Long id;
	private String descricao;

	public PontoConsumoSituacaoDTO (PontoConsumoSituacao entity) {
        	super();
    	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}
