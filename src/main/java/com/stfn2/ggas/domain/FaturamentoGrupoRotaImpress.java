package com.stfn2.ggas.domain;

import com.stfn2.ggas.config.security.domain.User;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.FaturamentoGrupoRotaImpressDTO;
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
@Table(name = "FATURAMENTO_GRUPO_ROTA_IMPRESS")
public class FaturamentoGrupoRotaImpress extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FGRI_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FGRI")
	@SequenceGenerator(name = "SQ_FGRI", sequenceName = "SQ_FGRI_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FAGR_CD", referencedColumnName = "FAGR_CD")
	private FaturamentoGrupo faturamentoGrupo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROTA_CD", referencedColumnName = "ROTA_CD")
	private Rota rota;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USSI_CD", referencedColumnName = "USSI_CD")
	private User usuario;

	@Column(name = "FGRI_DT_AM_REFERENCIA", length = 6)
	private Integer anoMesReferencia = 0;

	@Column(name = "FGRI_DATA_IMPRESSAO")
	private LocalDateTime dataImpressao;

	@Column(name = "FGRI_DATA_ULTIMA_IMPRESSAO")
	private LocalDateTime dataUltimaImpressao;

	@Column(name = "FGRI_NR_CICLO")
	private Integer ciclo = 0;

	@Column(name = "FGRI_SQ_IMPRESSAO")
	private Integer sequenciaImpressao = 0;
	
	@Column(name = "FGRI_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "FGRI_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "FGRI_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public FaturamentoGrupoRotaImpress(Long id) {
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
		return MapperImpl.parseObject(this, FaturamentoGrupoRotaImpressDTO.class);
	}
}