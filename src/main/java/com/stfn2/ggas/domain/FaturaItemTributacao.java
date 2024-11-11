package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.FaturaItemTributacaoDTO;
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
@Table(name = "FATURA_ITEM_TRIBUTACAO")
public class FaturaItemTributacao extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FATB_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FATB")
	@SequenceGenerator(name = "SQ_FATB", sequenceName = "SQ_FATB_CD", allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "FAIT_CD", referencedColumnName = "FAIT_CD")
	private FaturaItem faturaItem;

	@ManyToOne
	@JoinColumn(name = "TRIB_CD", referencedColumnName = "TRIB_CD")
	private Tributo tributo;

	@Column(name = "FATB_VL_BASE_CALCULO")
	private BigDecimal baseCalculoTributo = BigDecimal.ZERO;

	@Column(name = "FATB_VL_BASE_SUBST")
	private BigDecimal baseCalculoTributoSubstitutivo = BigDecimal.ZERO;

	@Column(name = "FATB_PR_ALIQUOTA")
	private BigDecimal percentualTributo = BigDecimal.ZERO;

	@Column(name = "FATB_VL_VALOR_IMPOSTO")
	private BigDecimal valorTributo = BigDecimal.ZERO;

	@Column(name = "FATB_VL_SUBST")
	private BigDecimal valorTributoSubstitutivo = BigDecimal.ZERO;

	@Column(name = "FATB_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "FATB_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "FATB_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public FaturaItemTributacao(Long id) {
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
		return MapperImpl.parseObject(this, FaturaItemTributacaoDTO.class);
	}
}