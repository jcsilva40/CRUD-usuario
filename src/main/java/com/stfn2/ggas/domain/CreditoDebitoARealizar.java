package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.CreditoDebitoARealizarDTO;
import com.stfn2.ggas.domain.enumTypes.CreditoDebitoSituacaoEnum;
import com.stfn2.ggas.domain.enumTypes.SituacaoPagamentoEnum;
import com.stfn2.ggas.domain.enumTypes.converter.CreditoDebitoSituacaoConverter;
import com.stfn2.ggas.domain.enumTypes.converter.SituacaoPagamentoConverter;
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
@Table(name = "CREDITO_DEBITO_A_REALIZAR")
public class CreditoDebitoARealizar extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CRDR_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CRDR")
	@SequenceGenerator(name = "SQ_CRDR", sequenceName = "SQ_CRDR_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CRDN_CD", referencedColumnName = "CRDN_CD")
	private CreditoDebitoNegociado creditoDebitoNegociado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEVO_CD", referencedColumnName = "DEVO_CD")
	private Devolucao devolucao;

	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CRDS_CD", referencedColumnName = "CRDS_CD")
	private CreditoDebitoSituacao creditoDebitoSituacao;*/

	@Column(name = "CRDS_CD")
	@Convert(converter = CreditoDebitoSituacaoConverter.class)
	private CreditoDebitoSituacaoEnum creditoDebitoSituacao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FAGE_CD_ORIGEM", referencedColumnName = "FAGE_CD")
	private FaturaGeral	faturaGeral;

	@Column(name = "ENCO_CD_SITUACAO_PAGAMENTO")
	@Convert(converter = SituacaoPagamentoConverter.class)
	private SituacaoPagamentoEnum situacaoPagamento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ENCO_CD_MOTIVO_CANCELAMENTO", referencedColumnName = "ENCO_CD")
	private EntidadeConteudo situacaoCancelamento;

	@Column(name = "CRDR_DT_AM_FATURAMENTO", length = 6)
	private Integer anoMesFaturamento = 0;

	@Column(name = "CRDR_DT_AM_COBRANCA", length = 6)
	private Integer anoMesCobranca;

	@Column(name = "CRDR_DT_ANTECIPACAO")
	private LocalDateTime dataAntecipacao;

	@Column(name = "CRDR_DT_VENCIMENTO")
	private LocalDateTime dataVencimento;

	@Column(name = "CRDR_QN")
	private BigDecimal quantidade = BigDecimal.ZERO;

	@Column(name = "CRDR_VL_ANTECIPACAO")
	private BigDecimal valorAntecipacao = BigDecimal.ZERO;

	@Column(name = "CRDR_VL")
	private BigDecimal valor = BigDecimal.ZERO;

	@Column(name = "CRDR_NR_PRESTACAO")
	private BigDecimal numeroPrestacao = BigDecimal.ZERO;

	@Column(name = "CRDR_NR_CICLO")
	private Integer ciclo = 0;

	@Column(name = "CRDR_IN_ANTECIPACAO")
	private Boolean antecipacao = true;

	@Column(name = "CRDR_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "CRDR_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "CRDR_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public CreditoDebitoARealizar(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public String getDescricao() {
		return null;
	}

	@Override
	public void setDescricao(String descricao) {

	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, CreditoDebitoARealizarDTO.class);
	}
}