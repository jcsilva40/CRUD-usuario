package com.stfn2.ggas.domain.dtos.basic;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.CampanhaDesconto;
import com.stfn2.ggas.domain.enumTypes.ModalidadeMedicaoEnum;
import com.stfn2.ggas.domain.enumTypes.SegmentoCampanhaEnum;
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
public class CampanhaDescontoBasicDTO extends BaseDTO {
        
        private Long id;
	
        private String descricao; //Nome campanha
        
        private BigDecimal porcentagem;
        
        private Long segmentoPaiId;
        
        private ModalidadeMedicaoEnum modalidadeMedicao;
        
        private LocalDate inicioAdesao;
               
        private LocalDate encerramentoAdesao;
        
        private Short periodoVigencia;
        
        private Integer quantidadeParticipante;
        
        private String mensagemFatura;
        
        private Boolean habilitado;

    	public CampanhaDescontoBasicDTO (CampanhaDesconto entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}