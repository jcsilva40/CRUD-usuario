package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.MedidorOperacaoHistoricoDTO;
import com.stfn2.ggas.domain.enumTypes.MedidorLocalInstalacaoEnum;
import com.stfn2.ggas.domain.enumTypes.converter.MedidorLocalInstalacaoConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MEDIDOR_OPERACAO_HISTORICO")
public class MedidorOperacaoHistorico extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MEOH_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_MEOH")
	@SequenceGenerator(name = "SQ_MEOH", sequenceName = "SQ_MEOH_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEDI_CD", referencedColumnName = "MEDI_CD")
	private Medidor medidor;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "FUNC_CD", referencedColumnName = "FUNC_CD")
//	private Funcionario funcionario;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "MEOP_CD", referencedColumnName = "MEOP_CD")
//	private MedidorOperacao medidorOperacao;

	@Column(name = "FUNC_CD")
	private Long funcionarioId;

	@Column(name = "MEOP_CD")
	private Long medidorOperacaoId;

	@Column(name = "MEMP_CD")
	private Long medidorMotivoOperacaoId;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "MEMP_CD", referencedColumnName = "MEMP_CD")
//	private MedidorMotivoOperacao medidorMotivoOperacao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POCN_CD", referencedColumnName = "POCN_CD")
	private PontoConsumo pontoConsumo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UNID_CD_PRESSAO_ANTERIOR", referencedColumnName = "UNID_CD")
	private Unidade unidadePressaoAnterior;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEIN_CD", referencedColumnName = "MEIN_CD")
	private MedidorInstalacao medidorInstalacao;

	@Convert(converter = MedidorLocalInstalacaoConverter.class)
	@Column(name = "MELI_CD")
	private MedidorLocalInstalacaoEnum localInstalacao;

	@Column(name = "MEOH_DS")
	private String descricaoHistoricoOperacoes;

	@Column(name = "MEOH_DT_PLANEJADA")
	private LocalDate dataPlanejada;

	@Column(name = "MEOH_DT_REALIZADA")
	private LocalDate dataRealizada;

	@Column(name = "MEOH_MD_LEITURA")
	private BigDecimal numeroLeitura;

	@Column(name = "MEOH_MD_PRESSAO_ANTERIOR")
	private BigDecimal medidaPressaoAnterior;

	@Column(name = "MEOH_NR_LACRE")
	private String lacre;

	@Column(name = "MEOH_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "MEOH_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "MEOH_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public MedidorOperacaoHistorico(Long id) {
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
		return MapperImpl.parseObject(this, MedidorOperacaoHistoricoDTO.class);
	}
}