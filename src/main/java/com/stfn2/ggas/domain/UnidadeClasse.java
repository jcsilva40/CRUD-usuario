package com.stfn2.ggas.domain;

import java.time.LocalDateTime;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;

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
@Table(name = "UNIDADE_CLASSE")
public class UnidadeClasse extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "UNCL_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_UNCL")
	@SequenceGenerator(name = "SQ_UNCL", sequenceName = "SQ_UNCL_CD", allocationSize = 1)
	private Long id;

	@Basic
	@Column(name = "UNCL_DS", nullable = false, length = 20)
	private String descricao;
	@Basic
	@Column(name = "UNCL_DS_ABREVIADO", nullable = true, length = 6)
	private String abreviado;
	
	@Column(name = "UNCL_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "UNCL_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "UNCL_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();
        
        public UnidadeClasse(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		// TODO Auto-generated method stub
		return null;
	}

}
