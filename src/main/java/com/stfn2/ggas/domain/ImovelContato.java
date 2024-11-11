package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.ImovelContatoDTO;
import com.stfn2.ggas.domain.enumTypes.ContatoTipoEnum;
import com.stfn2.ggas.domain.enumTypes.converter.ContatoTipoConverter;
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
@Table(name = "IMOVEL_CONTATO")
public class ImovelContato extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "IMCO_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_IMCO")
	@SequenceGenerator(name = "SQ_IMCO", sequenceName = "SQ_IMCO_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IMOV_CD", referencedColumnName = "IMOV_CD")
	private Imovel imovel;

	@Convert(converter = ContatoTipoConverter.class)
	@Column(name = "COTI_CD")
	private ContatoTipoEnum contatoTipo;

	@Column(name = "IMCO_NM")
	private String descricao;

	@Column(name = "IMCO_DS_CARGO")
	private String cargo;

	@Column(name = "IMCO_CD_DDD")
	private String ddd;

	@Column(name = "IMCO_NR_FONE")
	private String telefone;

	@Column(name = "IMCO_NR_RAMAL")
	private String ramal;

	@Column(name = "IMCO_DS_EMAIL")
	private String email;

	@Column(name = "IMCO_IN_PRINCIPAL")
	private Boolean principal = true;

	@Column(name = "IMCO_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "IMCO_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "IMCO_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public ImovelContato(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, ImovelContatoDTO.class);
	}
}