package com.stfn2.ggas.domain;

import java.time.LocalDateTime;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.IndiceFinanceiroValorNominalDTO;
import com.stfn2.ggas.domain.dtos.PaisDTO;

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
@Table(name = "PAIS")
public class Pais extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PAIS_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PAIS")
	@SequenceGenerator(name = "SQ_PAIS", sequenceName = "SQ_PAIS_CD", allocationSize = 1)
	private Long id;

	@Column(name = "PAIS_DS")
	private String descricao;

	@Column(name = "PAIS_CD_BACEN")
	private Integer codBacen;

	@Column(name = "PAIS_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "PAIS_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "PAIS_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public Pais(Long id) {
		super(id);
		this.id = id;
	}


	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, PaisDTO.class);
	}
		
}
