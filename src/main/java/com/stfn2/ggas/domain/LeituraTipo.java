package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.LeituraTipoDTO;
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
@Table(name = "LEITURA_TIPO")
public class LeituraTipo extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "LETI_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_LETI")
	@SequenceGenerator(name = "SQ_LETI", sequenceName = "SQ_LETI_CD", allocationSize = 1)
	private Long id;

	@Column(name = "LETI_DS")
	private String	descricao;
	
	@Column(name = "LETI_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "LETI_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "LETI_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public LeituraTipo(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, LeituraTipoDTO.class);
	}
}
