package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.SerieDTO;
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
@Table(name = "SERIE")
public class Serie extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SERI_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_SERI")
	@SequenceGenerator(name = "SQ_SERI", sequenceName = "SQ_SERI_CD", allocationSize = 1)
	private Long id;

	@Column(name = "SERI_NR")
	private String numero;

	@Column(name = "SERI_DS")
	private String descricao;
	
	@Column(name = "SERI_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "SERI_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "SERI_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public Serie(Long id) {
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
		return MapperImpl.parseObject(this, SerieDTO.class);
	}
}