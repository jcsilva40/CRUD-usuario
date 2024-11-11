package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.RecursoDTO;
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
@Table(name = "RECURSO")
public class Recurso extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "RECU_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_RECU")
	@SequenceGenerator(name = "SQ_RECU", sequenceName = "SQ_RECU_CD", allocationSize = 1)
	private Long id;

	@Column(name = "RECU_URL")
	private String url;

	@Column(name = "RECU_VERBO_HTTP")
	private String verboHttp;

	@Column(name = "RECU_CONSIDERAR_VERBO")
	private Boolean considerarVerbo = true;
	
	@Column(name = "RECU_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "RECU_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "RECU_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();


	public Recurso(Long id) {
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
		return MapperImpl.parseObject(this, RecursoDTO.class);
	}
}