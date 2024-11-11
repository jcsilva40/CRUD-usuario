package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.GrupoDTO;
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
@Table(name = "GRUPO")
public class Grupo extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "GRUP_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_GRUP")
	@SequenceGenerator(name = "SQ_GRUP", sequenceName = "SQ_GRUP_CD", allocationSize = 1)
	private Long id;
	
	@Column(name = "GRUP_DS")
	private String descricao;

	@Column(name = "GRUP_DS_COMPLEMENTO")
	private String complemento;

	@Column(name = "GRUP_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "GRUP_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "GRUP_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();


	public Grupo(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, GrupoDTO.class);
	}
}