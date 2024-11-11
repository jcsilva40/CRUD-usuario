package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.NaturezaOperacaoDTO;
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
@Table(name = "NATUREZA_OPERACAO")
public class NaturezaOperacao extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "NAOP_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_NAOP")
	@SequenceGenerator(name = "SQ_NAOP", sequenceName = "SQ_NAOP_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ENCO_CD_TIPO_OPERACAO", referencedColumnName = "ENCO_CD")
	private EntidadeConteudo tipoOperacao;

	@Column(name = "NAOP_DS")
	private String descricao;

	@Column(name = "NAOP_DS_NOTA")
	private String notaExplicativa;

	@Column(name = "NAOP_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "NAOP_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "NAOP_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public NaturezaOperacao(Long id) {
		super(id);
		this.id = id;
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, NaturezaOperacaoDTO.class);
	}
}