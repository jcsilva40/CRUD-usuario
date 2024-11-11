package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.BIJasperDTO;
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
@Table(name = "CP_BI_JASPER")
public class BIJasper extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "BIJA_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_BIJA_CD")
	@SequenceGenerator(name = "SQ_BIJA_CD", sequenceName = "SQ_BIJA_CD", allocationSize = 1)
	private Long id;
	
	@Column(name = "IN_USO")
	private Boolean habilitado = true;

	@Column(name = "NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	@Column(name = "BODY_2")
	private String conteudo;

	public BIJasper(Long id) {
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
		return MapperImpl.parseObject(this, BIJasperDTO.class);
	}
}