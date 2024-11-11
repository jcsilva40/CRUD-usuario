package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.PermissaoDTO;
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
@Table(name = "PERMISSAO")
public class Permissao extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PERM_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PERM")
	@SequenceGenerator(name = "SQ_PERM", sequenceName = "SQ_PERM_CD", allocationSize = 1)
	private Long id;

	@Column(name = "PERM_DS")
	private String descricao;

	@Column(name = "PERM_DS_COMPLEMENTO")
	private String complemento;

	@Column(name = "PERM_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "PERM_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "PERM_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public Permissao(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, PermissaoDTO.class);
	}
}