package com.stfn2.ggas.domain;

import com.stfn2.ggas.config.security.domain.User;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.FaturaImpressaoDTO;
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
@Table(name = "FATURA_IMPRESSAO")
public class FaturaImpressao extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FAIM_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FAIM")
	@SequenceGenerator(name = "SQ_FAIM", sequenceName = "SQ_FAIM_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ENCO_CD_TIPO_CONVENIO", referencedColumnName = "ENCO_CD")
	private EntidadeConteudo tipoConvenio;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FATU_CD", referencedColumnName = "FATU_CD")
	private Fatura fatura;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FAAN_CD", referencedColumnName = "FAAN_CD")
	private FaturamentoAnormalidade faturamentoAnormalidade;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FAGR_CD", referencedColumnName = "FAGR_CD")
	private FaturamentoGrupo faturamentoGrupo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROTA_CD", referencedColumnName = "ROTA_CD")
	private Rota rota;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USSI_CD_IMPRESSAO", referencedColumnName = "USSI_CD")
	private User usuario;

	@Column(name = "FAIM_DT_AM_REFERENCIA")
	private Integer anoMesReferencia = 0;

	@Column(name = "FAIM_DT_GERACAO")
	private LocalDate dataGeracao = LocalDate.now();

	@Column(name = "FAIM_DT_IMPRESSAO")
	private LocalDate dataImpressao;

	@Column(name = "FAIM_IN_IMPRESSAO")
	private Boolean impressao;

	@Column(name = "FAIM_NR_LOTE")
	private Integer lote = 0;

	@Column(name = "FAIM_SQ_IMPRESSAO")
	private Integer sequenciaImpressao = 0;

	@Column(name = "FAIM_VL")
	private BigDecimal valorFatura = BigDecimal.ZERO;
	
	@Column(name = "FAIM_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "FAIM_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "FAIM_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public FaturaImpressao(Long id) {
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
		return MapperImpl.parseObject(this, FaturaImpressaoDTO.class);
	}
}