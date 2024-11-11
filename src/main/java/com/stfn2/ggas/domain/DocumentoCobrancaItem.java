package com.stfn2.ggas.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.DocumentoCobrancaItemDTO;
import com.stfn2.ggas.domain.enumTypes.CobrancaDebitoSituacaoEnum;
import com.stfn2.ggas.domain.enumTypes.converter.CobrancaDebitoSituacaoConverter;
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
@Table(name = "DOCUMENTO_COBRANCA_ITEM")
public class DocumentoCobrancaItem extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "DOCI_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DOCI")
	@SequenceGenerator(name = "SQ_DOCI", sequenceName = "SQ_DOCI_CD", allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "DOCO_CD", referencedColumnName = "DOCO_CD")
	@JsonBackReference
	private DocumentoCobranca documentoCobranca;

	@ManyToOne
	@JoinColumn(name = "FAGE_CD", referencedColumnName = "FAGE_CD")
	private FaturaGeral faturaGeral;

	@ManyToOne
	@JoinColumn(name = "CRDR_CD", referencedColumnName = "CRDR_CD")
	private CreditoDebitoARealizar creditoDebitoARealizar;

	@ManyToOne
	@JoinColumn(name = "POCN_CD", referencedColumnName = "POCN_CD")
	private PontoConsumo pontoConsumo;

	@Column(name = "CODS_CD")
	@Convert(converter = CobrancaDebitoSituacaoConverter.class)
	private CobrancaDebitoSituacaoEnum cobrancaDebitoSituacao;

	@Column(name = "DOCI_VL")
	private BigDecimal valor;

	@Column(name = "DOCI_VL_ACRESCIMOS")
	private BigDecimal valorAcrescimo;

	@Column(name = "DOCI_VL_DESCONTOS")
	private BigDecimal valorDesconto;

	@Column(name = "DOCI_NR_PRESTACAO")
	private Integer numeroPrestacao;

	@Column(name = "DOCI_NR_TOTAL_PRESTACAO")
	private Integer numeroTotalPrestacao;

	@Column(name = "DOCI_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "DOCI_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "DOCI_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public DocumentoCobrancaItem(Long id) {
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
		return MapperImpl.parseObject(this, DocumentoCobrancaItemDTO.class);
	}
}