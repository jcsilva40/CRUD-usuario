package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.MedidorMotivoOperacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedidorMotivoOperacaoDTO extends BaseDTO {
        private Long id;

    	public MedidorMotivoOperacaoDTO (MedidorMotivoOperacao entity) {super(); 	}

}