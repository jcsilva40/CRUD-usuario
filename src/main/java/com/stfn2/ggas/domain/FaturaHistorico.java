package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.FaturaHistoricoDTO;
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
@Table(name = "CPGAS_FATURA_HISTORICO")
public class FaturaHistorico extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CHAVE_PRIMARIA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CPGAS_FATURA_HISTORICO")
	@SequenceGenerator(name = "SQ_CPGAS_FATURA_HISTORICO", sequenceName = "SQ_CPGAS_FATURA_HISTORICO_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FATU_CD", referencedColumnName = "FATU_CD")
	private Fatura fatura;

	@Column(name = "nota_fiscal")
	private String notaFiscalFatura;

	@Column(name = "pagamento")
	private String pagamento;

	@Column(name = "VALOR")
	private String valor;

	@Column(name = "VENCIMENTO")
	private String vencimento;

	@Column(name = "VOLUME_MEDIDO")
	private String volumeMedido;

	@Column(name = "ANOMES")
	private Integer anoMes;

	@Column(name = "HABILITADO")
	private Boolean habilitado = true;

	@Column(name = "VERSAO")
	private Integer versao = 0;

	@Column(name = "ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public FaturaHistorico(Long id) {
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
		return MapperImpl.parseObject(this, FaturaHistoricoDTO.class);
	}
}