package com.stfn2.ggas.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CONTA_CONTABIL")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ContaContabil extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "COCO_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_COCO")
	@SequenceGenerator(name = "SQ_COCO", sequenceName = "SQ_COCO_CD", allocationSize = 1)
	private Long id;

	@Column(name = "COCO_DS", length = 40)
	private String descricao;

	@Column(name = "COCO_NR_CONTA", length = 30)
	private String numeroConta;

	@Column(name = "COCO_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "COCO_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "COCO_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public ContaContabil(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return null;
	}
}

