package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.*;
import com.stfn2.ggas.domain.enumTypes.AmortizacaoEnum;
import com.stfn2.ggas.domain.enumTypes.ItemFaturaEnum;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RubricaDTO extends BaseDTO {

	private Long id;
	private Integer maximoParcela;
	private BigDecimal minimoEntrada;
	private BigDecimal valorReferencia;
	private BigDecimal valorMaximo;

	private Long financiamentoTipoId;
	private String financiamentoTipoDescricao;
	private ItemFaturaEnum itemFatura;
	private AmortizacaoEnum amortizacao;
	private String descricaoImpressao;
	private Boolean outrosServicos;
	private Long nomeclaturaComumMercosulId;
	private String codigoSubsTributario;  //max 7 char


	private String descricao;  //not null
	private Boolean entradaObrigatoria; //not null
	private Long lancamentoItemContabilId; //not null
	private Long unidadeId;  //not null
	private Boolean regulamentada; //not null  //if true, definir dataInicioVigencia para Rubrica_Valor_regulamentado
	//private LocalDate dataInicioVigencia;
//	private LocalDate dataFimVigencia;

	private LocalDateTime dataInicioVigenciaRegulamentada;
	private LocalDateTime dataFimVigenciaRegulamentada;
	private LocalDateTime dataInicioVigenciaTributo;
	private LocalDateTime dataFimVigenciaTributo;

	private Boolean usoSistema; //not null
	private Boolean cobrancaMulta; //not null
	private Boolean cobrancaJuros; //not null
	private Boolean composicaoNF; //not null
	private Boolean DescricaoComplementar; //not null
	private Boolean isItemFaturamento; //not null
	private List<Long> tributosId;


	public RubricaDTO (Rubrica entity) {
        	super();
    	}

	@Override
	public Long getId() {
		return this.id;
	}
}
