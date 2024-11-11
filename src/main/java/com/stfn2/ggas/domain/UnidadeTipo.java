package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.UnidadeTipoDTO;
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
@Table(name = "UNIDADE_TIPO")
public class UnidadeTipo extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "UNTI_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_UNTI")
	@SequenceGenerator(name = "SQ_UNTI", sequenceName = "SQ_UNTI_CD", allocationSize = 1)
	private Long id;

	@Column(name = "UNTI_DS")
	private String descricao;

	@Column(name = "UNTI_DS_ABREVIADO")
	private String abreviado;

	@Column(name = "UNTI_CD_TIPO")
	private String tipo;

	@Column(name = "UNTI_NR_NIVEL")
	private Integer nivel;
	
	@Column(name = "UNTI_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "UNTI_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "UNTI_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public UnidadeTipo(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, UnidadeTipoDTO.class);
	}
}
