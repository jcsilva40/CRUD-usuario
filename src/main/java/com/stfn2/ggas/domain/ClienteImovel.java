package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.ClienteImovelDTO;
import com.stfn2.ggas.domain.enumTypes.ClienteImovelFimRelacionamentoMotivoEnum;
import com.stfn2.ggas.domain.enumTypes.ClienteImovelRelacionamentoEnum;
import com.stfn2.ggas.domain.enumTypes.converter.ClienteImovelFimRelacionamentoMotivoConverter;
import com.stfn2.ggas.domain.enumTypes.converter.ClienteImovelRelacionamentoConverter;
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
@Table(name = "CLIENTE_IMOVEL")
public class ClienteImovel extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CLIM_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CLIM")
	@SequenceGenerator(name = "SQ_CLIM", sequenceName = "SQ_CLIM_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IMOV_CD", referencedColumnName = "IMOV_CD")
	private Imovel imovel;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIE_CD", referencedColumnName = "CLIE_CD")
	private Cliente cliente;

	@Convert(converter = ClienteImovelRelacionamentoConverter.class)
	@Column(name = "CLIR_CD")
	private ClienteImovelRelacionamentoEnum relacionamento;

	@Convert(converter = ClienteImovelFimRelacionamentoMotivoConverter.class)
	@Column(name = "CLIF_CD")
	private ClienteImovelFimRelacionamentoMotivoEnum clienteImovelFimRelacionamentoMotivoEnum;

	@Column(name = "CLIM_TM_RELACAO_FIM")
	private LocalDate fimRelacionamento;


	@Column(name = "CLIM_TM_RELACAO_INICIO")
	private LocalDate inicioRelacionamento;

	@Column(name = "CLIM_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "CLIM_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "CLIM_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public ClienteImovel(Long id) {
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
		return MapperImpl.parseObject(this, ClienteImovelDTO.class);
	}
}