package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.ServicoPrestadoDTO;
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
@Table(name = "SERVICO_PRESTADO")
public class ServicoPrestado extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SEPR_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_SEPR")
	@SequenceGenerator(name = "SQ_SEPR", sequenceName = "SQ_SEPR_CD", allocationSize = 1)
	private Long id;

	@Column(name = "SEPR_DS")
	private String descricao;
	
	@Column(name = "SEPR_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "SEPR_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "SEPR_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public ServicoPrestado(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, ServicoPrestadoDTO.class);
	}
}
