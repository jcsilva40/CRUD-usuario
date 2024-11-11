package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.FaturamentoCronogramaDTO;
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
@Table(name = "FATURAMENTO_CRONOGRAMA")
public class FaturamentoCronograma extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FACR_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FACR")
	@SequenceGenerator(name = "SQ_FACR", sequenceName = "SQ_FACR_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FAGR_CD", referencedColumnName = "FAGR_CD")
	private FaturamentoGrupo faturamentoGrupo;

	@Column(name = "FACR_DT_AM_FATURAMENTO", length = 6)
	private Integer anoMesFaturamento = 0;

	@Column(name = "FACR_NR_CICLO")
	private Integer ciclo = 0;
	
	@Column(name = "FACR_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "FACR_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "FACR_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public FaturamentoCronograma(Long id) {
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
		return MapperImpl.parseObject(this, FaturamentoCronogramaDTO.class);
	}
}