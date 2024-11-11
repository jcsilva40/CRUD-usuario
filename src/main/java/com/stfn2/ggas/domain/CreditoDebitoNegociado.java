package com.stfn2.ggas.domain;

import com.stfn2.ggas.config.security.domain.User;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.CreditoDebitoNegociadoDTO;
import com.stfn2.ggas.domain.enumTypes.*;
import com.stfn2.ggas.domain.enumTypes.converter.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CREDITO_DEBITO_NEGOCIADO")
public class CreditoDebitoNegociado extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CRDN_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CRDN")
	@SequenceGenerator(name = "SQ_CRDN", sequenceName = "SQ_CRDN_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIE_CD", referencedColumnName = "CLIE_CD")
	private Cliente cliente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CROR_CD", referencedColumnName = "CROR_CD")
	private CreditoOrigem creditoOrigem;

	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ENCO_CD_AMORTIZACAO", referencedColumnName = "ENCO_CD")
	private EntidadeConteudo amortizacao;*/
	@Column(name = "ENCO_CD_AMORTIZACAO")
	@Convert(converter = AmortizacaoConverter.class)
	private AmortizacaoEnum amortizacao;

	@Column(name =  "ENCO_CD_FORMA_COBRANCA")
	@Convert(converter = FormaCobrancaParcelamentoConverter.class)
	private FormaCobrancaParcelamentoEnum formaCobranca;

	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ENCO_CD_PERIODICIDADE_COBRANCA", referencedColumnName = "ENCO_CD")
	private EntidadeConteudo periodicidadeCobranca;*/

	@Column(name = "ENCO_CD_PERIODICIDADE_COBRANCA")
	@Convert(converter = TipoPeriodicidadeConverter.class)
	private TipoPeriodicidadeEnum periodicidadeCobranca;

	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ENCO_CD_PERIODICIDADE_JUROS", referencedColumnName = "ENCO_CD")
	private EntidadeConteudo periodicidadeJuros;*/

	@Column(name =  "ENCO_CD_PERIODICIDADE_JUROS")
	@Convert(converter = PeriodicidadeJurosConverter.class)
	private PeriodicidadeJurosEnum periodicidadeJuros;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ENCO_CD_EVENTO_INICIO_COBRANCA", referencedColumnName = "ENCO_CD")
	private EntidadeConteudo eventoInicioCobranca;

	@Column(name =  "ENCO_CD_STATUS")
	@Convert(converter = StatusAutorizacaoConverter.class)
	private StatusAutorizacaoEnum status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FITI_CD", referencedColumnName = "FITI_CD")
	private FinanciamentoTipo financiamentoTipo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POCN_CD", referencedColumnName = "POCN_CD")
	private PontoConsumo pontoConsumo;

	/*@ManyToOne
	@JoinColumn(name = "RECI_CD", referencedColumnName = "RECI_CD")
	private Recebimento recebimento;*/

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RUBR_CD", referencedColumnName = "RUBR_CD")
	private Rubrica rubrica;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SEGM_CD", referencedColumnName = "SEGM_CD")
	private Segmento segmento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USSI_CD", referencedColumnName = "USSI_CD")
	private User usuario;

	@Column(name = "CRDN_DT_INICIO_COBRANCA")
	private LocalDateTime dataCobranca;

	@Column(name = "CRDN_TM_FINANCIAMENTO")
	private LocalDateTime dataFinanciamento;

	@Column(name = "CRDN_NR_DIA_VENCIMENTO")
	private Integer diaVencimento = 0;

	@Column(name = "CRDN_DS")
	private String descricao;

	@Column(name = "CRDN_NR_DIAS_CARENCIA")
	private Integer diasCarencia = 0;

	@Column(name = "CRDN_QN_PRESTACAO_COBRADA")
	private Integer quantidadePrestacaoCobrada = 0;

	@Column(name = "CRDN_QN_PRESTACAO")
	private Integer quatidadePrestacoes = 0;

	@Column(name = "CRDN_PR_JUROS")
	private BigDecimal percentualJuros = BigDecimal.ZERO;

	@Column(name = "CRDN_VL_ENTRADA")
	private BigDecimal valorEntrada = BigDecimal.ZERO;

	@Column(name = "CRDN_VL")
	private BigDecimal valor = BigDecimal.ZERO;

	@Column(name = "CRDN_VL_JUROS")
	private BigDecimal valorJuros = BigDecimal.ZERO;

	@Column(name = "CRDN_DT_AM_INCLUSAO", length = 6)
	private Integer anoMesInclusao = 0;

	@Column(name = "CRDN_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "CRDN_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "CRDN_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public CreditoDebitoNegociado(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, CreditoDebitoNegociadoDTO.class);
	}
}