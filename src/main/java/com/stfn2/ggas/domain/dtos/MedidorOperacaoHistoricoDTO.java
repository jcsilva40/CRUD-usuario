package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.MedidorOperacaoHistorico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedidorOperacaoHistoricoDTO extends BaseDTO {
        private Long id;

    	public MedidorOperacaoHistoricoDTO (MedidorOperacaoHistorico entity) {super(); 	}

}