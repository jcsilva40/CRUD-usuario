package com.stfn2.ggas.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.IndiceFinanceiroValorNominalDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "INDICE_FINANCEIRO_VAL_NOMINAL")
public class IndiceFinanceiroValorNominal extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "INFV_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_INFV")
	@SequenceGenerator(name = "SQ_INFV", sequenceName = "SQ_INFV_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INFI_CD", referencedColumnName = "INFI_CD")
	private IndiceFinanceiro indiceFinanceiro;

	@Column(name = "INFV_DT_REFERENCIA")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataReferencia;

	@Column(name = "INFV_IN_UTILIZADO")
	private Boolean utilizado;

	@Column(name = "INFV_VL_NOMINAL")
	private BigDecimal valor;

	@Column(name = "INFV_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "INFV_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "INFV_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public IndiceFinanceiroValorNominal(Long id) {
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
		return MapperImpl.parseObject(this, IndiceFinanceiroValorNominalDTO.class);
	}
}
