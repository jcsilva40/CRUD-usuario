package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.RecursoPermissaoDTO;
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
@Table(name = "RECURSO_PERMISSOES")
public class RecursoPermissao extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "REPE_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_REPE")
	@SequenceGenerator(name = "SQ_REPE", sequenceName = "SQ_REPE_CD", allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "RECU_CD", referencedColumnName = "RECU_CD")
	private Recurso recurso;

	@ManyToOne
	@JoinColumn(name = "PERM_CD", referencedColumnName = "PERM_CD")
	private Permissao permissao;

	@Column(name = "REPE_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "REPE_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "REPE_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public RecursoPermissao(Long id) {
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
		return MapperImpl.parseObject(this, RecursoPermissaoDTO.class);
	}
}