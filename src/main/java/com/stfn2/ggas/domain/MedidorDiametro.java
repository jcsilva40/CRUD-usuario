package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.MedidorDiametroDTO;
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
@Table(name = "MEDIDOR_DIAMETRO")
public class MedidorDiametro extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MEDM_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_MEDM")
	@SequenceGenerator(name = "SQ_MEDM", sequenceName = "SQ_MEDM_CD", allocationSize = 1)
	private Long id;

	@Column(name = "MEDM_DS")
	private String descricao;

	@Column(name = "MEDM_DS_ABREVIADO")
	private String abreviado;

	@Column(name = "MEDM_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "MEDM_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "MEDM_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public MedidorDiametro(Long id) {
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
		return MapperImpl.parseObject(this, MedidorDiametroDTO.class);
	}
}