package com.stfn2.ggas.domain;

import java.time.LocalDateTime;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.ClienteDTO;
import com.stfn2.ggas.domain.dtos.EntidadeClasseDTO;

import jakarta.persistence.Basic;
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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ENTIDADE_CLASSE")
public class EntidadeClasse extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ENCL_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ENCL")
	@SequenceGenerator(name = "SQ_ENCL", sequenceName = "SQ_ENCL_CD", allocationSize = 1)
	private Long id;

	@Basic
	@Column(name = "ENCL_DS", nullable = false, length = 20)
	private String descricao;
	@Basic
	@Column(name = "ENCL_DS_ABREVIADO", nullable = true, length = 6)
	private String abreviado;
	
	public EntidadeClasse(Long id) {
		super(id);
		this.id = id;
	}

	@Column(name = "ENCL_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "ENCL_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "ENCL_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();
		
	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, EntidadeClasseDTO.class);
	}
}
