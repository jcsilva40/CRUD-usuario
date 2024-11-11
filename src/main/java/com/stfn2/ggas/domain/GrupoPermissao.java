package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.GrupoPermissaoDTO;
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
@Table(name = "GRUPO_PERMISSOES")
public class GrupoPermissao extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "GRPE_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_GRPE")
	@SequenceGenerator(name = "SQ_GRPE", sequenceName = "SQ_GRPE_CD", allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "GRUP_CD", referencedColumnName = "GRUP_CD")
	private Grupo grupo;

	@ManyToOne
	@JoinColumn(name = "PERM_CD", referencedColumnName = "PERM_CD")
	private Permissao permissao;
	
	@Column(name = "GRPE_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "GRPE_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "GRPE_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public GrupoPermissao(Long id) {
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
		return MapperImpl.parseObject(this, GrupoPermissaoDTO.class);
	}
}