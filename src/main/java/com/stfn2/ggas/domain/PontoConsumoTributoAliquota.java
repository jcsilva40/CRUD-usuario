package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.PontoConsumoTributoAliquotaDTO;
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
@Table(name = "PONTO_CONSUMO_TRIBUTO_ALIQUOTA")
public class PontoConsumoTributoAliquota extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "POTA_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_POTA")
	@SequenceGenerator(name = "SQ_POTA", sequenceName = "SQ_POTA_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRIB_CD", referencedColumnName = "TRIB_CD")
	private Tributo tributo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POCN_CD", referencedColumnName = "POCN_CD")
	private PontoConsumo pontoConsumo;

	@Column(name = "POTA_PR_ALIQUOTA")
	private BigDecimal valor = BigDecimal.ZERO;

	@Column(name = "POTA_IN_CREDITO_ICMS")
	private Boolean indicadorCreditoIcms = true;

	@Column(name = "POTA_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "POTA_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "POTA_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public PontoConsumoTributoAliquota(Long id) {
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
		return MapperImpl.parseObject(this, PontoConsumoTributoAliquotaDTO.class);
	}
}
