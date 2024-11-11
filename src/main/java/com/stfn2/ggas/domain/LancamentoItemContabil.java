package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.LancamentoItemContabilDTO;
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
@Table(name = "LANCAMENTO_ITEM_CONTABIL")
public class LancamentoItemContabil extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "LAIC_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_LAIC")
	@SequenceGenerator(name = "SQ_LAIC", sequenceName = "SQ_LAIC_CD", allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ENCO_CD_TIPO_DEBITO_CREDITO", referencedColumnName = "ENCO_CD")
	private EntidadeConteudo tipoDebitoCredito;

	@Column(name = "LAIC_DS")
	private String descricao;

	@Column(name = "LAIC_DS_ABREVIADO")
	private String abreviado;
	
	@Column(name = "LAIC_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "LAIC_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "LAIC_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public LancamentoItemContabil(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, LancamentoItemContabilDTO.class);
	}
}