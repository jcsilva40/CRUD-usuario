package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.DevolucaoDTO;
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
@Table(name = "DEVOLUCAO")
public class Devolucao extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "DEVO_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DEVO")
	@SequenceGenerator(name = "SQ_DEVO", sequenceName = "SQ_DEVO_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ENCO_CD_TIPO_DEVOLUCAO", referencedColumnName = "ENCO_CD")
	private EntidadeConteudo tipoDevolucao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIE_CD", referencedColumnName = "CLIE_CD")
	private Cliente	cliente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POCN_CD", referencedColumnName = "POCN_CD")
	private PontoConsumo pontoConsumo;

	@Column(name = "DEVO_DT")
	private LocalDateTime dataDevolucap;

	@Column(name = "DEVO_OBSERVACAO")
	private String observacao;

	@Column(name = "DEVO_VL")
	private BigDecimal valor = BigDecimal.ZERO;
	
	@Column(name = "DEVO_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "DEVO_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "DEVO_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public Devolucao(Long id) {
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
		return MapperImpl.parseObject(this, DevolucaoDTO.class);
	}
}