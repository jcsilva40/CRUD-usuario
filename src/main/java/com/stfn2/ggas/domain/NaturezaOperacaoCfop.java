package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.NaturezaOperacaoCfopDTO;
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
@Table(name = "NATUREZA_OPERACAO_CFOP")
public class NaturezaOperacaoCfop extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "NAOC_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_NAOC")
	@SequenceGenerator(name = "SQ_NAOC", sequenceName = "SQ_NAOC_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NAOP_CD", referencedColumnName = "NAOP_CD")
	private NaturezaOperacao naturezaOperacao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ENCO_CD_LOCALIZ_DEST_ADQUIRENT", referencedColumnName = "ENCO_CD")
	private EntidadeConteudo localizacaoDestinatario;

	@Column(name = "NAOC_CD_CFOP")
	private Integer codigoFiscal = 0;
	
	@Column(name = "NAOC_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "NAOC_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "NAOC_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public NaturezaOperacaoCfop(Long id) {
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
		return MapperImpl.parseObject(this, NaturezaOperacaoCfopDTO.class);
	}
}