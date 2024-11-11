package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.MedidorMarcaDTO;
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
@Table(name = "MEDIDOR_MARCA")
public class MedidorMarca extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MEMA_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_MEMA")
	@SequenceGenerator(name = "SQ_MEMA", sequenceName = "SQ_MEMA_CD", allocationSize = 1)
	private Long id;

	@Column(name = "MEMA_DS")
	private String descricao;

	@Column(name = "MEMA_DS_ABREVIADO")
	private String abreviado;

	@Column(name = "MEMA_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "MEMA_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "MEMA_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public MedidorMarca(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, MedidorMarcaDTO.class);
	}
}