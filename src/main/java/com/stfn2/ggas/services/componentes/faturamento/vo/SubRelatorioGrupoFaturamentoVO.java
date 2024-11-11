package com.stfn2.ggas.services.componentes.faturamento.vo;

import com.stfn2.ggas.constante.Constantes;
import com.stfn2.ggas.tools.ToolNumber;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class SubRelatorioGrupoFaturamentoVO implements Serializable {
	@Serial
	private static final long serialVersionUID = -2012818218923680730L;

	private BigDecimal valorResidencial;
	private BigDecimal valorComercial;
	private BigDecimal valorIndustrial;
	private BigDecimal valorOutros;
	private String descricaoRubrica;

	public SubRelatorioGrupoFaturamentoVO() {

		this.valorResidencial = BigDecimal.ZERO;
		this.valorComercial = BigDecimal.ZERO;
		this.valorIndustrial = BigDecimal.ZERO;
		this.valorOutros = BigDecimal.ZERO;
		this.descricaoRubrica = "";
	}

	public String getValorResidencialFormatado() {
		return ToolNumber.converterCampoCurrencyParaString(valorResidencial, Constantes.LOCALE_PADRAO);
	}

	public String getValorComercialFormatado() {

		return ToolNumber.converterCampoCurrencyParaString(valorComercial, Constantes.LOCALE_PADRAO);
	}

	public String getValorIndustrialFormatado() {

		return ToolNumber.converterCampoCurrencyParaString(valorIndustrial, Constantes.LOCALE_PADRAO);
	}

	public String getValorOutrosFormatado() {
		return ToolNumber.converterCampoCurrencyParaString(valorOutros, Constantes.LOCALE_PADRAO);
	}
}
