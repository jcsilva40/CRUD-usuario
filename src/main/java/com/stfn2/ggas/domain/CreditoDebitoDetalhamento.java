package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.CreditoDebitoDetalhamentoDTO;
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
@Table(name = "CREDITO_DEBITO_DETALHAMENTO")
public class CreditoDebitoDetalhamento extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CRDD_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CRDD")
	@SequenceGenerator(name = "SQ_CRDD", sequenceName = "SQ_CRDD_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CRDR_CD", referencedColumnName = "CRDR_CD")
	private CreditoDebitoARealizar creditoDebitoARealizar;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LAIC_CD", referencedColumnName = "LAIC_CD")
	private LancamentoItemContabil lancamentoItemContabil;

	@Column(name = "CRDD_VL")
	private BigDecimal valor = BigDecimal.ZERO;

	@Column(name = "CRDD_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "CRDD_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "CRDD_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public CreditoDebitoDetalhamento(Long id) {
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
		return MapperImpl.parseObject(this, CreditoDebitoDetalhamentoDTO.class);
	}
}