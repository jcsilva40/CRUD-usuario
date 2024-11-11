package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.ModuloSistemaDTO;
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
@Table(name = "MODULO_SISTEMA")
public class ModuloSistema extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MOSI_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_MOSI")
	@SequenceGenerator(name = "SQ_MOSI", sequenceName = "SQ_MOSI_CD", allocationSize = 1)
	private Long id;

	@Column(name = "MOSI_DS")
	private String descricao;

	@Column(name = "MOSI_IN_CONTABIL")
	private Boolean contabil;
	
	@Column(name = "MOSI_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "MOSI_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "MOSI_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public ModuloSistema(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, ModuloSistemaDTO.class);
	}
}