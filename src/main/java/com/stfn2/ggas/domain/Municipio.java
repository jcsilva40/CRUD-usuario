package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.MunicipioDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MUNICIPIO")
public class Municipio extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MUNI_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_MUNI")
	@SequenceGenerator(name = "SQ_MUNI", sequenceName = "SQ_MUNI_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UNFE_CD", referencedColumnName = "UNFE_CD")
	private UnidadeFederacao uf;

	@Column(name = "MUNI_DS")
	private String descricao;

	@Column(name = "MUNI_CD_DDD")
	private Integer DDD;


	@Column(name = "MUNI_CD_IBGE")
	private Integer codIbge;

	@Column(name = "MUNI_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "MUNI_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "MUNI_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public Municipio(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, MunicipioDTO.class);
	}


	/*
	@ManyToOne
	@JoinColumn(name = "MICR_CD", referencedColumnName = "MICR_CD")
	private Microrregiao microrregiao;
	* */
	@Column(name = "MICR_CD")
	public Long microrRegiaoLegado = 41037L;

	/*

	@ManyToOne
	@JoinColumn(name = "REGI_CD", referencedColumnName = "REGI_CD")
	private Regiao regiao;




	cepInicial;

	cepFinal
	* */
		
}
