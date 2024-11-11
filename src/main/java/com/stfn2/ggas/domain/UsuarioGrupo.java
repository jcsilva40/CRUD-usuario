package com.stfn2.ggas.domain;

import com.stfn2.ggas.config.security.domain.User;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.UsuarioGrupoDTO;
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
@Table(name = "USUARIO_GRUPOS")
public class UsuarioGrupo extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USGR_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_USGR")
	@SequenceGenerator(name = "SQ_USGR", sequenceName = "SQ_USGR_CD", allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "USSI_CD", referencedColumnName = "USSI_CD")
	private User usuario;

	@ManyToOne
	@JoinColumn(name = "GRUP_CD", referencedColumnName = "GRUP_CD")
	private Grupo grupo;
	
	@Column(name = "USGR_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "USGR_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "USGR_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public UsuarioGrupo(Long id) {
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
		return MapperImpl.parseObject(this, UsuarioGrupoDTO.class);
	}
}