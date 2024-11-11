package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.FaturaMotivoRevisaoDTO;
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
@Table(name = "FATURA_MOTIVO_REVISAO")
public class FaturaMotivoRevisao extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FAMR_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FAMR")
	@SequenceGenerator(name = "SQ_FAMR", sequenceName = "SQ_FAMR_CD", allocationSize = 1)
	private Long id;

	@Column(name = "FAMR_DS")
	private String descricao;

	@Column(name = "FAMR_IN_RETIRADA_AUTOMATICA")
	private Boolean returadaAutomatica = false;

	@Column(name = "FAMR_NR_DIAS_PRAZO_MAXIMO")
	private Integer diasPrazoMaximo = 0;

	@Column(name = "FAMR_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "FAMR_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "FAMR_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public FaturaMotivoRevisao(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, FaturaMotivoRevisaoDTO.class);
	}
}