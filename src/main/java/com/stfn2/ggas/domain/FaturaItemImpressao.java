package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.FaturaItemImpressaoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FATURA_ITEM_IMPRESSAO")
public class FaturaItemImpressao extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FAII_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FAII")
	@SequenceGenerator(name = "SQ_FAII", sequenceName = "SQ_FAII_CD", allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "TARI_CD", referencedColumnName = "TARI_CD")
	private Tarifa tarifa;

	@ManyToOne
	@JoinColumn(name = "FAIT_CD", referencedColumnName = "FAIT_CD")
	private FaturaItem faturaItem;

	@Column(name = "FAII_DS_IMPRESSAO")
	private String descricaoItem;

	@Column(name = "FAII_DS_DESCONTO")
	private String descricaoDesconto;

	@Column(name = "FAII_DT_INICIAL")
	private LocalDate dataInicial;

	@Column(name = "FAII_DT_FINAL")
	private LocalDate dataFinal;

	@Column(name = "FAII_QN_DIAS_CONSUMO")
	private Integer diasConsumo = 0;

	@Column(name = "FAII_VL_DESCONTO")
	private BigDecimal valorDesconto = BigDecimal.ZERO;

	@Column(name = "FAII_TOTAL")
	private BigDecimal valorTotal = BigDecimal.ZERO;

	@Column(name = "FAII_VL_UNITARIO")
	private BigDecimal valorUnitario = BigDecimal.ZERO;

	@Column(name = "FAII_MD_CONSUMO")
	private BigDecimal consumo = BigDecimal.ZERO;

	@Column(name = "FAII_IN_DESCONTO")
	private Boolean desconto = false;

	@Column(name = "FAII_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "FAII_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "FAII_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public FaturaItemImpressao(Long id) {
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
		return MapperImpl.parseObject(this, FaturaItemImpressaoDTO.class);
	}
}