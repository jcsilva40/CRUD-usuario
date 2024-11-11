package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.FaturamentoAtividadeCronogrDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FATURAMENTO_ATIVIDADE_CRONOGR")
public class FaturamentoAtividadeCronograma extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FAAC_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FAAC")
	@SequenceGenerator(name = "SQ_FAAC", sequenceName = "SQ_FAAC_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ATSI_CD", referencedColumnName = "ATSI_CD")
	private AtividadeSistema atividadeSistema;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FACR_CD", referencedColumnName = "FACR_CD")
	private FaturamentoCronograma faturamentoCronograma;

	@Column(name = "FAAC_DT_INICIO")
	private LocalDate dataInicio;

	@Column(name = "FAAC_DT_FIM")
	private LocalDate dataFim;

	@Column(name = "FAAC_TM_REALIZACAO")
	private LocalDate dataRealizacao;
	
	@Column(name = "FAAC_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "FAAC_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "FAAC_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public FaturamentoAtividadeCronograma(Long id) {
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
		return MapperImpl.parseObject(this, FaturamentoAtividadeCronogrDTO.class);
	}
}