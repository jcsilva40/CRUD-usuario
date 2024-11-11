package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.RecursoSistemaDTO;
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
@Table(name = "RECURSO_SISTEMA")
public class RecursoSistema extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "RESI_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_RESI")
	@SequenceGenerator(name = "SQ_RESI", sequenceName = "SQ_RESI_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OPSI_CD", referencedColumnName = "OPSI_CD")
	private OperacaoSistema operacaoSistema;

	@Column(name = "RESI_DS")
	private String descricao;

	@Column(name = "RESI_IN_WEB")
	private Boolean web;

	@Column(name = "RESI_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "RESI_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "RESI_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public RecursoSistema(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, RecursoSistemaDTO.class);
	}
}