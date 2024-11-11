package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.RotaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ROTA")
public class Rota extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ROTA_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ROTA")
	@SequenceGenerator(name = "SQ_ROTA", sequenceName = "SQ_ROTA_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LEIT_CD", referencedColumnName = "LEIT_CD")
	private Leiturista leiturista;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LEIT_CD_SUPLENTE", referencedColumnName = "LEIT_CD")
	private Leiturista leituristaSuplente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPR_CD", referencedColumnName = "EMPR_CD")
	private Empresa empresa;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERI_CD", referencedColumnName = "PERI_CD")
	private Periodicidade periodicidade;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LETI_CD", referencedColumnName = "LETI_CD")
	private LeituraTipo leituraTipo = new LeituraTipo(1L);

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FAGR_CD", referencedColumnName = "FAGR_CD")
	private FaturamentoGrupo faturamentoGrupo;

	@OneToMany(mappedBy = "rota", fetch = FetchType.LAZY)
	private List<Imovel> listaImoveis = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "SECO_CD", referencedColumnName = "SECO_CD")
	private SetorComercial setorComercial;

	@Column(name = "ROTA_NR")
	private String descricao;

	@Column(name = "ROTA_NR_MAX_PONTOS_CONSUMO")
	private Integer numeroMaximoPontos;

	@Column(name = "ROTA_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "ROTA_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "ROTA_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public Rota(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, RotaDTO.class);
	}
}
