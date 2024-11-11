package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.ClienteDebitoAutomaticoDTO;
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
@Table(name = "CLIENTE_DEBITO_AUTOMATICO")
public class ClienteDebitoAutomatico extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CLDA_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CLDA")
	@SequenceGenerator(name = "SQ_CLDA", sequenceName = "SQ_CLDA_CD", allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "CLIE_CD", referencedColumnName = "CLIE_CD")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "IMOV_CD", referencedColumnName = "IMOV_CD")
	private Imovel imovel;

	@ManyToOne
	@JoinColumn(name = "CONT_CD", referencedColumnName = "CONT_CD")
	private Contrato contrato;
	
	@Column(name = "CLDA_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "CLDA_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "CLDA_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public ClienteDebitoAutomatico(Long id) {
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
		return MapperImpl.parseObject(this, ClienteDebitoAutomaticoDTO.class);
	}
}