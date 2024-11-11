package com.stfn2.ggas.domain.dtos.basic;
import com.stfn2.ggas.core.interfaces.BaseDTO;

import com.stfn2.ggas.domain.Imovel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImovelBasicDTO extends BaseDTO {
        private Long id;
		private String descricao;
		private LocalDate dataEntrega;
		private Integer nip;
		private Integer quantidadePontosConsumo;
		private String endereco;
		private String grupoRota;

    	public ImovelBasicDTO (Imovel entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}

}