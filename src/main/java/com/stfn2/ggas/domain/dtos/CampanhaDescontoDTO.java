package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.CampanhaDesconto;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CampanhaDescontoDTO extends BaseDTO {
    
        private Long id;
        
	private String nomeCampanha; 
        
        private Long tipoDescontoId;
        
        private BigDecimal porcentagem;
        
        private LocalDate inicioAdesao;
        
        private Short periodoVigencia;
        
        private LocalDate encerramentoAdesao;
        
        private String mensagemFatura;
        
        private Long tipoCampanhaId;
        
        private Long segmentoPaiId;
        
        private Long modalidadeMedicaoId;
        
        private BigDecimal valorUnitarioPorVolume;
                
	private Long quantidadePorVolume;

    	public CampanhaDescontoDTO (CampanhaDesconto entity) {super(); 	}

    	@Override
    	public Long getId() {
            return this.id;
    	}
}