package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.FaturamentoMensagemCobrancaDTO;
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
@Table(name = "FATURAMENTO_MENSAGEM_COBRANCA")
public class FaturamentoMensagemCobranca extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FAMC_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FAMC")
	@SequenceGenerator(name = "SQ_FAMC", sequenceName = "SQ_FAMC_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ENCO_CD_FORMA_COBRANCA", referencedColumnName = "ENCO_CD")
	private EntidadeConteudo formaCobranca;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FAME_CD", referencedColumnName = "FAME_CD")
	private FaturamentoMensagem faturamentoMensagem;

	@Column(name = "FAMC_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "FAMC_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "FAMC_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public FaturamentoMensagemCobranca(Long id) {
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
		return MapperImpl.parseObject(this, FaturamentoMensagemCobrancaDTO.class);
	}
}