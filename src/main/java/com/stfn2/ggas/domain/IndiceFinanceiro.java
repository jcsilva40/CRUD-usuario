package com.stfn2.ggas.domain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.IndiceFinanceiroDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "INDICE_FINANCEIRO")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class IndiceFinanceiro extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "INFI_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_INFI")
	@SequenceGenerator(name = "SQ_INFI", sequenceName = "SQ_INFI_CD", allocationSize = 1)
	private Long id;

	@Column(name = "INFI_DS", length = 150)
	private String descricao;

	@Column(name = "INFI_DS_ABREVIADO", length = 10)
	private String abreviado;

	@Column(name = "INFI_IN_MENSAL", length = 1)
	private Boolean mensal;

	@Column(name = "INFI_IN_PERMITE_NEGATIVO", length = 1)
	private Boolean negativo;

	@Column(name = "INFI_IN_VALOR_PERCENTUAL", length = 1)
	private Boolean percentual;

	@Column(name = "INFI_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "INFI_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "INFI_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();
	
	public IndiceFinanceiro(Long id) {
		super(id);
		this.id = id;
	}
		
	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, IndiceFinanceiroDTO.class);
	}        
}
