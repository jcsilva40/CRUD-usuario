package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.CorretorVazao;
import com.stfn2.ggas.domain.enumTypes.MedidorSituacaoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CorretorVazaoDTO extends BaseDTO {
        private Long id;
		private String numeroSerie;
		private MedidorSituacaoEnum medidorSituacaoEnum;

    	public CorretorVazaoDTO (CorretorVazao entity) {super(); 	}

}