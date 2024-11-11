package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.PrecoMedioPonderadoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CPGAS_PRECO_MEDIO_PONDERADO")
public class PrecoMedioPonderado extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PRMP_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PRMP")
	@SequenceGenerator(name = "SQ_PRMP", sequenceName = "SQ_PRMP_CD", allocationSize = 1)
	private Long id;

	@Column(name="PRMP_REFERENCIA")
	private Integer referencia;

	@Column(name="PRMP_DATA_FIM")
	private LocalDate dataFim;

	@Column(name="PRMP_DATA")
	private LocalDate dataVigencia;

	@Column(name="PRMP_VALOR")
	private BigDecimal valor;
	
	@Column(name = "PRMP_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "PRMP_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	@Transient
	private Integer versao = 0;

	public PrecoMedioPonderado(Long id) {
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
		return MapperImpl.parseObject(this, PrecoMedioPonderadoDTO.class);
	}
}