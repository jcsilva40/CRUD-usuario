package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.TemperaturaTrabalhoFaixaDTO;
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
@Table(name = "TEMPERATURA_TRABALHO_FAIXA")
public class TemperaturaTrabalhoFaixa extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "TETF_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TETF")
	@SequenceGenerator(name = "SQ_TETF", sequenceName = "SQ_TETF_CD", allocationSize = 1)
	private Long id;
	
	@Column(name = "TETF_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "TETF_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "TETF_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public TemperaturaTrabalhoFaixa(Long id) {
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
		return MapperImpl.parseObject(this, TemperaturaTrabalhoFaixaDTO.class);
	}
}