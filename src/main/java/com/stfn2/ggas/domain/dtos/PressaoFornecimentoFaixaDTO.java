package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.EntidadeConteudo;
import com.stfn2.ggas.domain.PressaoFornecimentoFaixa;
import com.stfn2.ggas.domain.Segmento;
import com.stfn2.ggas.domain.Unidade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PressaoFornecimentoFaixaDTO extends BaseDTO {
        private Long id;
		private BigDecimal medidaMinimo;
		private BigDecimal medidaMaximo;
		private BigDecimal numeroFatorZ;
		private BigDecimal numeroFatorCorrecaoPTZPCS;
		private Unidade unidadePressao;
		private Segmento segmento;
		private EntidadeConteudo classePressao;
		private EntidadeConteudo indicadorCorrecaoPT;
		private EntidadeConteudo indicadorCorrecaoZ;

    	public PressaoFornecimentoFaixaDTO (PressaoFornecimentoFaixa entity) {super(); 	}

    	@Override
    	public Long getId() {
        	return this.id;
    	}
}