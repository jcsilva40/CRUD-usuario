package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.FaturamentoMensagemVencimentDTO;
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
@Table(name = "FATURAMENTO_MENSAGEM_VENCIMENT")
public class FaturamentoMensagemVenciment extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FAMV_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FAMV")
	@SequenceGenerator(name = "SQ_FAMV", sequenceName = "SQ_FAMV_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FAME_CD", referencedColumnName = "FAME_CD")
	private FaturamentoMensagem faturamentoMensagem;

	@Column(name = "FAMV_NR_DIA_VENCIMENTO")
	private Integer diaVencimento = 0;
	
	@Column(name = "FAMV_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "FAMV_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "FAMV_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public FaturamentoMensagemVenciment(Long id) {
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
		return MapperImpl.parseObject(this, FaturamentoMensagemVencimentDTO.class);
	}
}