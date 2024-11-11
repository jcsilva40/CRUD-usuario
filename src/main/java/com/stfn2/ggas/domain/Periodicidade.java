package com.stfn2.ggas.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.PeriodicidadeDTO;
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
@Table(name = "PERIODICIDADE")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Periodicidade extends BaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	private final static int APENAS_UM_CICLO = 1;

	@Id
	@Column(name = "PERI_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PERI")
	@SequenceGenerator(name = "SQ_PERI", sequenceName = "SQ_PERI_CD", allocationSize = 1)
	private Long id;

	@Column(name = "PERI_DS", length = 20)
	private String descricao;

	@Column(name = "PERI_DS_ABREVIADO", length = 5)
	private String abreviado;

	@Column(name = "PERI_IN_MES_CIVIL", length = 1)
	private Boolean mesCivil = true;

	@Column(name = "PERI_NR_MAX_DIA_CICLO", length = 3)
	private Integer maximoDiasCiclo;

	@Column(name = "PERI_NR_MIN_DIA_CICLO", length = 3)
	private Integer minimoDiasCiclo;

	@Column(name = "PERI_QN_DIA", length = 3)
	private Integer quantidadeDias;

	@Column(name = "PERI_QN_CICLO", length = 3)
	private Integer quantidadeCiclos;

	@Column(name = "PERI_IN_USO", length = 3)
	private Boolean habilitado = true;

	@Column(name = "PERI_NR_VERSAO", length = 3)
	private Integer versao = 0;

	@Column(name = "PERI_TM_ULTIMA_ALTERACAO", length = 3)
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public Periodicidade(Long id) {
		super(id);
		this.id = id;
	}

	public boolean isMultiplusCiclos() {
		return this.getQuantidadeCiclos() > APENAS_UM_CICLO;
	}

	public String getLabelCombo() {
		return getDescricao();
	}


	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, PeriodicidadeDTO.class);
	}
}
