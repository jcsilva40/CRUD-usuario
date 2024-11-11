package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.SetorComercialDTO;
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
@Table(name = "SETOR_COMERCIAL")
public class SetorComercial extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SECO_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_SECO")
	@SequenceGenerator(name = "SQ_SECO", sequenceName = "SQ_SECO_CD", allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "MUNI_CD", referencedColumnName = "MUNI_CD")
	private Municipio municipio;

	@Column(name = "SECO_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "SECO_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "SECO_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public SetorComercial(Long id) {
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
		return MapperImpl.parseObject(this, SetorComercialDTO.class);
	}
}