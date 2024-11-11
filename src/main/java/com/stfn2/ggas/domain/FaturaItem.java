package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.FaturaItemDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FATURA_ITEM")
public class FaturaItem extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FAIT_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FAIT")
	@SequenceGenerator(name = "SQ_FAIT", sequenceName = "SQ_FAIT_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NAOC_CD", referencedColumnName = "NAOC_CD")
	private NaturezaOperacaoCfop naturezaOperacaoCfop;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FATU_CD", referencedColumnName = "FATU_CD")
	private Fatura fatura;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CRDR_CD", referencedColumnName = "CRDR_CD")
	private CreditoDebitoARealizar creditoDebitoARealizar;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RUBR_CD", referencedColumnName = "RUBR_CD")
	private Rubrica rubrica;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SEGM_CD", referencedColumnName = "SEGM_CD")
	private Segmento segmento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TARI_CD", referencedColumnName = "TARI_CD")
	private Tarifa tarifa;

	@OneToMany(mappedBy = "faturaItem", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FaturaItemImpressao> faturaImpressao = new ArrayList<>();

	@OneToMany(mappedBy = "faturaItem", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FaturaItemTributacao> faturaItemTributacao = new ArrayList<>();

	@Column(name = "FAIT_MD_CONSUMO")
	private BigDecimal consumo = BigDecimal.ZERO;

	@Column(name = "FAIT_QN")
	private BigDecimal quantidadeItem = BigDecimal.ZERO;

	@Column(name = "FAIT_VL_FIXO")
	private BigDecimal valorFixoFaixaTarifa = BigDecimal.ZERO;

	@Column(name = "FAIT_VL_VARIAVEL")
	private BigDecimal valorVariavelFaixaTarifa = BigDecimal.ZERO;

	@Column(name = "FAIT_VL_UNITARIO")
	private BigDecimal valorUnitario = BigDecimal.ZERO;

	@Column(name = "FAIT_VL_TOTAL")
	private BigDecimal valorTotal = BigDecimal.ZERO;

	@Column(name = "FAIT_NR_SEQUENCIAL")
	private Integer sequenciaItemNota = 0;

	@Column(name = "FAIT_QN_TOTAL_PRESTACAO")
	private Integer totalPrestacoesPendentes = 0;

	@Column(name = "FAIT_MD_INICIO")
	private Integer volumeInicialFaixa = 0;

	@Column(name = "FAIT_MD_FIM")
	private Integer volumeFinalFaixa = 0;

	@Column(name = "FAIT_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "FAIT_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "FAIT_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public FaturaItem(Long id) {
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
		return MapperImpl.parseObject(this, FaturaItemDTO.class);
	}
}