package com.stfn2.ggas.domain;

import com.stfn2.ggas.config.security.domain.User;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.UsuarioPermissoesDTO;
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
@Table(name = "USUARIO_PERMISSOES")
public class UsuarioPermissoes extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USPE_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_USPE")
	@SequenceGenerator(name = "SQ_USPE", sequenceName = "SQ_USPE_CD", allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "USSI_CD", referencedColumnName = "USSI_CD")
	private User usuario;

	@ManyToOne
	@JoinColumn(name = "PERM_CD", referencedColumnName = "PERM_CD")
	private Permissao permissao;
	
	@Column(name = "USPE_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "USPE_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "USPE_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public UsuarioPermissoes(Long id) {
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
		return MapperImpl.parseObject(this, UsuarioPermissoesDTO.class);
	}
}