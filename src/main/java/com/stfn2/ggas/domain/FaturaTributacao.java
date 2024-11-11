package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.FaturaTributacaoDTO;
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
@Table(name = "FATURA_TRIBUTACAO")
public class FaturaTributacao extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FATR_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FATR")
	@SequenceGenerator(name = "SQ_FATR", sequenceName = "SQ_FATR_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FATU_CD", referencedColumnName = "FATU_CD")
	private Fatura fatura;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRIB_CD", referencedColumnName = "TRIB_CD")
	private Tributo tributo;

	@Column(name = "FATR_VL_BASE_CALCULO")
	private BigDecimal baseCalculoTributo = BigDecimal.ZERO;

	@Column(name = "FATR_VL_BASE_SUBST")
	private BigDecimal baseCalculoTributoSubstitutivo = BigDecimal.ZERO;

	@Column(name = "FATR_PR_ALIQUOTA")
	private BigDecimal percentualTributo = BigDecimal.ZERO;

	@Column(name = "FATR_VL_VALOR_IMPOSTO")
	private BigDecimal valorTributo = BigDecimal.ZERO;

	@Column(name = "FATR_VL_SUBST")
	private BigDecimal valorTributoSubstitutivo = BigDecimal.ZERO;
	
	@Column(name = "FATR_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "FATR_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "FATR_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public FaturaTributacao(Long id) {
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
		return MapperImpl.parseObject(this, FaturaTributacaoDTO.class);
	}
}