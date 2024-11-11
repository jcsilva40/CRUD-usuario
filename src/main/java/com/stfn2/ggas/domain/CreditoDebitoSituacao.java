package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.CreditoDebitoSituacaoDTO;
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
@Table(name = "CREDITO_DEBITO_SITUACAO")
public class CreditoDebitoSituacao extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CRDS_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CRDS")
	@SequenceGenerator(name = "SQ_CRDS", sequenceName = "SQ_CRDS_CD", allocationSize = 1)
	private Long id;

	@Column(name = "CRDS_DS")
	private String descricao;

	@Column(name = "CRDS_DS_ABREVIADA")
	private String abreviado;

	@Column(name = "CRDS_IN_VALIDO")
	private Boolean valido = true;

	@Column(name = "CRDS_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "CRDS_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "CRDS_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public CreditoDebitoSituacao(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, CreditoDebitoSituacaoDTO.class);
	}
}