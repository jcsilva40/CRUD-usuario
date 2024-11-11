package com.stfn2.ggas.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Unidade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnidadeConsumidoraDTO extends BaseDTO {
        private Long id;

		@JsonProperty("segmentoId")
		private Long ramoAtividadeSegmentoId;
		private Long ramoAtividadeId;

		@JsonProperty("segmentoDescricao")
		private String ramoAtividadeSegmentoDescricao;
		private String ramoAtividadeDescricao;
       	private Integer quantidadeEconomica;

    	public UnidadeConsumidoraDTO(Unidade entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}