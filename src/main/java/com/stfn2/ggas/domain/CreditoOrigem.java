package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.CreditoOrigemDTO;
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
@Table(name = "CREDITO_ORIGEM")
public class CreditoOrigem extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CROR_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CROR")
	@SequenceGenerator(name = "SQ_CROR", sequenceName = "SQ_CROR_CD", allocationSize = 1)
	private Long id;

	@Column(name = "CROR_DS")
	private String descricao;

	@Column(name = "CROR_DS_ABREVIADO")
	private String abreviado;
	
	@Column(name = "CROR_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "CROR_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "CROR_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public CreditoOrigem(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, CreditoOrigemDTO.class);
	}
}