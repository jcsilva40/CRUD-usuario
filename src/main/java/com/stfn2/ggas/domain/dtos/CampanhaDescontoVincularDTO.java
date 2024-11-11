package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.CampanhaDescontoVincular;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CampanhaDescontoVincularDTO extends BaseDTO {
        private Long id;	
        
        private Long campanhaDescontoId;
        
        private Long contratoId;
        
        private Long imovelId;
        
        private List<Long> listaContratoId;
        
        private List<Long> listaImovelId;
        
        private Long solicitanteId;
        
        private LocalDate dataSolicitacao;
        
        private LocalDateTime dataAprovacao;
        
        private Long aprovadorId;

    	public CampanhaDescontoVincularDTO (CampanhaDescontoVincular entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}