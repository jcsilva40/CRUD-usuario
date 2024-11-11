package com.stfn2.ggas.domain;

import com.stfn2.ggas.config.security.domain.User;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.FaturamentoAnomaliaHistoricoDTO;
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
@Table(name = "FATURAMENTO_ANOMALIA_HISTORICO")
public class FaturamentoAnomaliaHistorico extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FAAH_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FAAH")
	@SequenceGenerator(name = "SQ_FAAH", sequenceName = "SQ_FAAH_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FATU_CD", referencedColumnName = "FATU_CD")
	private Fatura fatura;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIE_CD", referencedColumnName = "CLIE_CD")
	private Cliente cliente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FAAN_CD", referencedColumnName = "FAAN_CD")
	private FaturamentoAnormalidade faturamentoAnormalidade;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POCN_CD", referencedColumnName = "POCN_CD")
	private PontoConsumo pontoConsumo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USSI_CD_ANALISADOR", referencedColumnName = "USSI_CD")
	private User usuario;

	@Column(name = "FAAH_DT_AM_FATURAMENTO", length = 6)
	private Integer anoMesFaturamento = 0;

	@Column(name = "FAAH_DT_ANALISE")
	private LocalDateTime dataRealizacaoAnalise;

	@Column(name = "FAAH_DT_GERACAO")
	private LocalDateTime dataGeracaoAnormalidade;

	@Column(name = "FAAH_DS_COMPLEMENTAR")
	private String descricaoComplementar;

	@Column(name = "FAAH_NR_CICLO")
	private Integer ciclo = 0;

	@Column(name = "FAAH_IN_ANALISADA")
	private Boolean analisada;

	@Column(name = "FAAH_IN_CORRECAO")
	private Boolean corrigida = true;

	@Column(name = "FAAH_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "FAAH_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "FAAH_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public FaturamentoAnomaliaHistorico(Long id) {
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
		return MapperImpl.parseObject(this, FaturamentoAnomaliaHistoricoDTO.class);
	}
}