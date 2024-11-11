package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.MedidorCapacidadeDTO;
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
@Table(name = "MEDIDOR_CAPACIDADE")
public class MedidorCapacidade extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MECA_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_MECA")
	@SequenceGenerator(name = "SQ_MECA", sequenceName = "SQ_MECA_CD", allocationSize = 1)
	private Long id;

	@Column(name = "MECA_DS")
	private String descricao;

	@Column(name = "MECA_DS_ABREVIADO")
	private String abreviado;

	@Column(name = "MECA_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "MECA_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "MECA_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public MedidorCapacidade(Long id) {
		super(id);
		this.id = id;
	}
	
	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, MedidorCapacidadeDTO.class);
	}
}