package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.AtividadeSistemaDTO;
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
@Table(name = "ATIVIDADE_SISTEMA")
public class AtividadeSistema extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ATSI_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ATSI")
	@SequenceGenerator(name = "SQ_ATSI", sequenceName = "SQ_ATSI_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ATSI_CD_PRECEDENTE", referencedColumnName = "ATSI_CD")
	private AtividadeSistema atividadeSistemaPrecedente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OPSI_CD", referencedColumnName = "OPSI_CD")
	private OperacaoSistema operacaoSistema;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MOSI_CD", referencedColumnName = "MOSI_CD")
	private ModuloSistema moduloSistema;

	@Column(name = "ATSI_DS")
	private String descricao;

	@Column(name = "ATSI_DS_EMAIL_REMETENTE")
	private String emailRemetente;

	@Column(name = "ATSI_DS_EMAIL")
	private String email;

	@Column(name = "ATSI_NR_HORA_INICIAL_PROCES")
	private Integer horaInicialProcesso = 0;

	@Column(name = "ATSI_NR_HORA_FINAL_PROCES")
	private Integer horaFinalProcesso = 0;

	@Column(name = "ATSI_IN_OBRIGATORIEDADE")
	private Boolean obrigatoriedade;

	@Column(name = "ATSI_IN_EXTERNA")
	private Boolean externa;

	@Column(name = "ATSI_IN_CRONOGRAMA")
	private Boolean cronograma;

	@Column(name = "ATSI_IN_DETALHA_ROTA")
	private Boolean detalhaRota;

	@Column(name = "ATSI_IN_AGENDAMENTO")
	private Boolean agendamento;

	@Column(name = "ATSI_IN_ENVIA_EMAIL")
	private Boolean enviaEmail;

	@Column(name = "ATSI_NR_SEQUENCIA")
	private Integer sequencia = 0;

	@Column(name = "ATSI_NR_DIAS_INTERVALO")
	private Integer diasIntervalo = 0;

	@Column(name = "ATSI_NR_DIAS_DURACAO")
	private Integer diasDuracao = 0;
	
	@Column(name = "ATSI_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "ATSI_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "ATSI_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public AtividadeSistema(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, AtividadeSistemaDTO.class);
	}
}