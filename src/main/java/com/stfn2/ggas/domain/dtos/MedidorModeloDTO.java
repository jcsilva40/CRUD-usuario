package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.MedidorModelo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedidorModeloDTO extends BaseDTO {
        private Long id;
        private String descricao;
        private String abreviado;

    	public MedidorModeloDTO (MedidorModelo entity) {super(); 	}

}