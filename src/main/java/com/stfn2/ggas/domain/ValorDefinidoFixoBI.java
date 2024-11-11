package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.ValorDefinidoFixoBIDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDateTime;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CP_BI_VALOR_DEFINIDO")
public class ValorDefinidoFixoBI extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "VADE_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_VADE_CD")
	@SequenceGenerator(name = "SQ_VADE_CD", sequenceName = "SQ_VADE_CD", allocationSize = 1)
	private Long id;
	
	@Column(name = "IN_USO")
	private Boolean habilitado = true;

	@Column(name = "NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	@Column(name = "DESCRICAO")
	private String descricao;

	@Column(name = "VALOR")
	private String valor;

	@ManyToOne
	@JoinColumn(name = "PABI_CD", referencedColumnName = "PABI_CD")
	private ParametroBI parametro;

	public ValorDefinidoFixoBI(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, ValorDefinidoFixoBIDTO.class);
	}
}