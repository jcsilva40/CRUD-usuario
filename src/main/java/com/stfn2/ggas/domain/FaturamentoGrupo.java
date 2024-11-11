package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.FaturamentoGrupoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FATURAMENTO_GRUPO")
public class FaturamentoGrupo extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FAGR_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FAGR")
	@SequenceGenerator(name = "SQ_FAGR", sequenceName = "SQ_FAGR_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERI_CD", referencedColumnName = "PERI_CD")
	private Periodicidade periodicidade;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LETI_CD", referencedColumnName = "LETI_CD")
	private LeituraTipo leituraTipo;

	@Column(name = "FAGR_DS")
	private String descricao;

	@Column(name = "FAGR_DS_ABREVIADO")
	private String abreviado;

	@Column(name = "FAGR_DT_AM_FATURAMENTO")
	private Integer anoMesFaturamento;

	@Column(name = "FAGR_NR_CICLO")
	private Integer numeroCiclo;

	@Column(name = "FAGR_QD_MAX_DIA_CICLO")
	private Integer qtdMaxDiaClo;

	@Column(name = "FAGR_QD_MIN_DIA_CICLO")
	private Integer qtdMinDiaClo;

	@Column(name = "FAGR_NR_DIA_VENCIMENTO")
	private Integer diaVencimento;

	@Column(name = "FAGR_IN_DT_VENC_ULT_DIA_CICLO")
	private Boolean vencimentoUltimoDiaCiclo;

	@Column(name = "FAGR_IN_CONTI_CASC_TARIFARIA")
	private Boolean cascataTarifaria;

	@Column(name = "FAGR_IN_VENCIMENT_IGUAL_FATURA")
	private Boolean vencimentoIgualFatura;

	@Column(name = "FAGR_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "FAGR_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "FAGR_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public FaturamentoGrupo(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, FaturamentoGrupoDTO.class);
	}
}
