package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.FeriadoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FERIADO")
public class Feriado extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FERI_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FERI")
	@SequenceGenerator(name = "SQ_FERI", sequenceName = "SQ_FERI_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MUNI_CD", referencedColumnName = "MUNI_CD")
	private Municipio municipio;

	@Column(name = "FERI_DT_FERIADO")
	private LocalDate dataFeriado;

	@Column(name = "FERI_DS_FERIADO")
	private String descricao;

	@Column(name = "FERI_IN_FERIADO_MUNICIPAL")
	private Boolean feriadoMunicipal = true;

	@Column(name = "FERI_IN_TIPO")
	private Boolean sempreMesmoDia = true;
	
	@Column(name = "FERI_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "FERI_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "FERI_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public Feriado(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, FeriadoDTO.class);
	}
}