package com.stfn2.ggas.domain.dtos.basic;
import com.stfn2.ggas.core.interfaces.BaseDTO;

import com.stfn2.ggas.domain.CreditoDebitoARealizar;
import com.stfn2.ggas.domain.enumTypes.CreditoDebitoSituacaoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditoDebitoARealizarBasicDTO extends BaseDTO {

		private Long id;
        private String pontoConsumoDescricao;
		private String pontoConsumoCil;
		private Integer anoMesFaturamento;
		private Integer ciclo;
		private String credDebNegComplemento;
		private String tipoCobrancaRubrDesc;
		private BigDecimal valor;
		private CreditoDebitoSituacaoEnum creditoDebitoSituacao;
		private Boolean executada;
		private Long pontoConsumoId;
		private String periodicidade;

		private Integer numeroPrestCobrada;


	public CreditoDebitoARealizarBasicDTO (CreditoDebitoARealizar entity) {super(); 	}

	@Override
	public Long getId() {
		return this.id;
	}
}