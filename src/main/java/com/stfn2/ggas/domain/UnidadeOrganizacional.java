package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.UnidadeOrganizacionalDTO;
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
@Table(name = "UNIDADE_ORGANIZACIONAL")
public class UnidadeOrganizacional extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "UNOR_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_UNOR")
	@SequenceGenerator(name = "SQ_UNOR", sequenceName = "SQ_UNOR_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPR_CD", referencedColumnName = "EMPR_CD")
	private Empresa empresa;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UNTI_CD", referencedColumnName = "UNTI_CD")
	private UnidadeTipo unidadeTipo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MUNI_CD", referencedColumnName = "MUNI_CD")
	private Municipio municipio;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UNOR_CD_SUPERIOR", referencedColumnName = "UNOR_CD")
	private UnidadeOrganizacional superior;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UNOR_CD_CENTRALIZADORA", referencedColumnName = "UNOR_CD")
	private UnidadeOrganizacional centralizadora;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UNOR_CD_REPAVIMENTADORA", referencedColumnName = "UNOR_CD")
	private UnidadeOrganizacional repavimentadora;

	/*@ManyToOne
	@JoinColumn(name = "CAAT_CD", referencedColumnName = "CAAT_CD")
	private CanalAtendimento canalAtendimento;

	@ManyToOne
	@JoinColumn(name = "GERE_CD", referencedColumnName = "GERE_CD")
	private GerenciaRegional gerenciaRegional;

	@ManyToOne
	@JoinColumn(name = "LOCA_CD", referencedColumnName = "LOCA_CD")
	private Localidade localidade;*/

	@Column(name = "UNOR_DS")
	private String descricao;

	@Column(name = "UNOR_DS_EMAIL_CONTATO")
	private String emailContato;

	@Column(name = "UNOR_TM_EXPEDIENTE_INICIO")
	private LocalDateTime inicioExpediente;

	@Column(name = "UNOR_TM_EXPEDIENTE_FIM")
	private LocalDateTime fimExpediente;

	@Column(name = "UNOR_SG")
	private String UF;

	@Column(name = "UNOR_IN_TRAMITE")
	private Boolean tramite;

	@Column(name = "UNOR_IN_CENTRAL_ATENDIMENTO")
	private Boolean centralAtendimento;

	@Column(name = "UNOR_IN_ABERTURA")
	private Boolean abertura;

	@Column(name = "UNOR_IN_CHAT")
	private Boolean chate;

	@Column(name = "UNOR_IN_USO_EMAIL")
	private Boolean usoEmail;

	@Column(name = "UNOR_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "UNOR_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "UNOR_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public UnidadeOrganizacional(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, UnidadeOrganizacionalDTO.class);
	}
}
