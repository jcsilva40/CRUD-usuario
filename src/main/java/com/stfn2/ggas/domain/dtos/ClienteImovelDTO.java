package com.stfn2.ggas.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.ClienteImovel;
import com.stfn2.ggas.domain.enumTypes.ClienteImovelFimRelacionamentoMotivoEnum;
import com.stfn2.ggas.domain.enumTypes.ClienteImovelRelacionamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteImovelDTO extends BaseDTO {

		private Long id;
		private Long imovelId;
		private Long clienteId;
		private String clienteDescricao;
		@JsonProperty("tipoRelacionamento")
		private ClienteImovelRelacionamentoEnum relacionamento;
		@JsonProperty("motivoFimRelacionamento")
		private ClienteImovelFimRelacionamentoMotivoEnum clienteImovelFimRelacionamentoMotivoEnum;
		private LocalDate fimRelacionamento;
		private LocalDate inicioRelacionamento;
		private Boolean habilitado;

    	public ClienteImovelDTO (ClienteImovel entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}