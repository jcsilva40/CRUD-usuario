package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.ContratoPontoConsumoItemFaturamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContratoPontoConsumoItemFaturamentoDTO extends BaseDTO {
        
        private Long id;
        
        private Long itemFaturaId;
        
        private Long tarifaId;
        
        private BigDecimal percentualMinimoQDC;
        
        private Integer numeroDiaVencimento;
    
        private Long faseReferenciaId;

        private Long opcaoFaseReferenciaId;
        
        private Boolean vencimentoDiaUtil;

    	public ContratoPontoConsumoItemFaturamentoDTO (ContratoPontoConsumoItemFaturamento entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}