package com.stfn2.ggas.domain.dtos.basic;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Fatura;
import com.stfn2.ggas.domain.enumTypes.SituacaoPagamentoEnum;
import com.stfn2.ggas.domain.enumTypes.StatusFaturaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FaturaBasicDTO extends BaseDTO {
      private Long id;
	  private Long rotaId;
	  private Integer numeroFatura;
	  private Integer anoMes;
	  private Integer ciclo;
	  private LocalDate dataEmissao;
	  private LocalDate dataVencimento;
	  private BigDecimal valorTotal;
	  private String periodoCiclo;
	  private String pontoConsumoDescricao;
	  private String segmentoDescricao;
	  private StatusFaturaEnum statusFatura;
	  private SituacaoPagamentoEnum situacaoPagamento;

    	public FaturaBasicDTO (Fatura entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}