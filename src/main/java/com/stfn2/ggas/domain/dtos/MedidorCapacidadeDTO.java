package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.MedidorCapacidade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedidorCapacidadeDTO extends BaseDTO {
        private Long id;
        @Getter
        private String descricao;

    	public MedidorCapacidadeDTO (MedidorCapacidade entity) {super(); 	}

}