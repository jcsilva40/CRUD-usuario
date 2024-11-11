package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.FaturamentoAnormalidadeDTO;
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
@Table(name = "FATURAMENTO_ANORMALIDADE")
public class FaturamentoAnormalidade extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FAAN_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FAAN")
	@SequenceGenerator(name = "SQ_FAAN", sequenceName = "SQ_FAAN_CD", allocationSize = 1)
	private Long id;

	@Column(name = "FAAN_DS")
	private String descricao;

	@Column(name = "FAAN_IN_IMPEDE_FATURAMENTO")
	private Boolean impedeFaturamento;

	@Column(name = "FAAN_IN_BLOQUEIA_FATURAMENTO")
	private Boolean bloqueiaFaturamento;

	@Column(name = "FAAN_IN_RESUMO_IMPRESSAO")
	private Boolean resumoImpressao;
	
	@Column(name = "FAAN_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "FAAN_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "FAAN_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public FaturamentoAnormalidade(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, FaturamentoAnormalidadeDTO.class);
	}
}