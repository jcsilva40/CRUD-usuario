package com.stfn2.ggas.domain;

import java.time.LocalDateTime;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.PessoaSexoDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serial;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PESSOA_SEXO")
public class PessoaSexo extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PESE_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PESE")
	@SequenceGenerator(name = "SQ_PESE", sequenceName = "SQ_PESE_CD", allocationSize = 1)
	private Long id;
	
	@Column(name = "PESE_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "PESE_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "PESE_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	@Column(name = "PESE_DS")
	private String descricao;

	public PessoaSexo(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, PessoaSexoDTO.class);
	}
}