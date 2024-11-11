package com.stfn2.ggas.domain.dtos.basic;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.MedidorInstalacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedidorInstalacaoBasicDTO extends BaseDTO {
        private Long id;
		private Long pontoConsumoId;
		private Long medidorId;
		private String pontoConsumoDescricao;
		private String medidorDescricao;
		private Long pontoConsumoCil;
		private LocalDate data;

    	public MedidorInstalacaoBasicDTO (MedidorInstalacao entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}