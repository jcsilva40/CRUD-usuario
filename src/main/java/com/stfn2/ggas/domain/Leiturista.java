package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.LeituristaDTO;
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
@Table(name = "LEITURISTA")
public class Leiturista extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "LEIT_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_LEIT")
	@SequenceGenerator(name = "SQ_LEIT", sequenceName = "SQ_LEIT_CD", allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "FUNC_CD", referencedColumnName = "FUNC_CD")
	private Funcionario funcionario;

	@Column(name = "LEIT_NR_IMEI")
	private Integer imeiAparelhoColeta;
	
	@Column(name = "LEIT_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "LEIT_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "LEIT_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public Leiturista(Long id) {
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
		return MapperImpl.parseObject(this, LeituristaDTO.class);
	}
}
