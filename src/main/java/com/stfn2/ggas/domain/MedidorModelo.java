package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.MedidorModeloDTO;
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
@Table(name = "MEDIDOR_MODELO")
public class MedidorModelo extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MEMD_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_MEMD")
	@SequenceGenerator(name = "SQ_MEMD", sequenceName = "SQ_MEMD_CD", allocationSize = 1)
	private Long id;

	@Column(name = "MEMD_DS")
	private String descricao;

	@Column(name = "MEMD_DS_ABREVIADA")
	private String abreviado;

	@Column(name = "MEMD_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "MEMD_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "MEMD_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public MedidorModelo(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, MedidorModeloDTO.class);
	}
}