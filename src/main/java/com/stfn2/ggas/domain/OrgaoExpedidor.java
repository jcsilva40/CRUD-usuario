package com.stfn2.ggas.domain;

import java.time.LocalDateTime;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.OrgaoExpedidorDTO;
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
import java.io.Serial;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORGAO_EXPEDIDOR_RG")
public class OrgaoExpedidor extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ORER_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ORER")
	@SequenceGenerator(name = "SQ_ORER", sequenceName = "SQ_ORER_CD", allocationSize = 1)
	private Long id;
	
	@Column(name = "ORER_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "ORER_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "ORER_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	@Column(name = "ORER_DS")
	private String descricao;

	@Column(name = "ORER_DS_ABREVIADO")
	private String abreviado;


	public OrgaoExpedidor(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public String getDescricao() {
		return descricao;
	}

	@Override
	public void setDescricao(String descricao) {

	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, OrgaoExpedidorDTO.class);
	}
}