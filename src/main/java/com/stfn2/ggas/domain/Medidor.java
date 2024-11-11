package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.MedidorDTO;
import com.stfn2.ggas.domain.enumTypes.MedidorLocalArmazenagemEnum;
import com.stfn2.ggas.domain.enumTypes.MedidorSituacaoEnum;
import com.stfn2.ggas.domain.enumTypes.MedidorTipoEnum;
import com.stfn2.ggas.domain.enumTypes.converter.MedidorLocalArmazenagemConverter;
import com.stfn2.ggas.domain.enumTypes.converter.MedidorSituacaoConverter;
import com.stfn2.ggas.domain.enumTypes.converter.MedidorTipoConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MEDIDOR")
public class Medidor extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MEDI_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_MEDI")
	@SequenceGenerator(name = "SQ_MEDI", sequenceName = "SQ_MEDI_CD", allocationSize = 1)
	private Long id;

	@OneToMany(mappedBy = "medidor", fetch = FetchType.LAZY)
	private List<MedidorMovimentacao> movimentacoes = new ArrayList<>();

	@OneToMany(mappedBy = "medidor", fetch = FetchType.LAZY)
	private List<MedidorInstalacao> listaInstalacaoMedidor = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "MEMA_CD", referencedColumnName = "MEMA_CD")
	private MedidorMarca medidorMarca;

//	@ManyToOne
//	@JoinColumn(name = "MEDM_CD", referencedColumnName = "MEDM_CD")
//	private MedidorDiametro medidorDiametro;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MECA_CD_MINIMO", referencedColumnName = "MECA_CD")
	private MedidorCapacidade capacidadeMinima = new MedidorCapacidade(21L);

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MECA_CD_MAXIMO", referencedColumnName = "MECA_CD")
	private MedidorCapacidade capacidadeMaxima = new MedidorCapacidade(21L);

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TETF_CD", referencedColumnName = "TETF_CD")
	private TemperaturaTrabalhoFaixa temperaturaTrabalhoFaixa = new TemperaturaTrabalhoFaixa(1L);

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UNID_CD_PRESSAO_MAXIMA", referencedColumnName = "UNID_CD")
	private Unidade unidadePressaoMaximaTrabalho = new Unidade(16L);

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMD_CD", referencedColumnName = "MEMD_CD")
	private MedidorModelo medidorModelo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ENCO_CD_SITUACAO_MEDI_IND", referencedColumnName = "ENCO_CD")
	private EntidadeConteudo situacaoAssociacaoMedidorIndependente = new EntidadeConteudo(553L);

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ENCO_CD_MODO_USO", referencedColumnName = "ENCO_CD")
	private EntidadeConteudo modoUso = new EntidadeConteudo(556L);

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CERTIFICADO_CD", referencedColumnName = "CHAVE_PRIMARIA")
	private CertificadoMedidor certificadoMedidor;

	@Convert(converter = MedidorTipoConverter.class)
	@Column(name = "METI_CD")
	private MedidorTipoEnum medidorTipoEnum = MedidorTipoEnum.NAO_INFORMADO;

	@Convert(converter = MedidorSituacaoConverter.class)
	@Column(name = "MESI_CD")
	private MedidorSituacaoEnum medidorSituacaoEnum = MedidorSituacaoEnum.PRONTO_PARA_INSTALAR;

	@Convert(converter = MedidorLocalArmazenagemConverter.class)
	@Column(name = "MELA_CD")
	private MedidorLocalArmazenagemEnum medidorLocalArmazenagemEnum = MedidorLocalArmazenagemEnum.NAO_INFORMADO;

	@Column(name = "MEDI_NR_SERIE")
	private String descricao;

	@Column(name = "MEDI_NR_ANO_FABRICACAO")
	private Integer anoFabricacao = 1984;

	@Column(name = "MEDI_DT_AQUISICAO")
	private LocalDateTime dataAquisicao;

//	@Column(name = "MEDI_DT_MAXIMA_INSTALACAO")
//	private LocalDateTime dataMaximaInstalacao;
//
//	@Column(name = "MEDI_DT_ULTIMA_CALIBRACAO")
//	private LocalDateTime dataUltimaCalibracao;

//	@Column(name = "MEDI_QN_ANO_CALIBRACAO")
//	private Integer anoCalibracao;

//	@Column(name = "MEDI_NR_TOMBO")
//	private String tombamento;

	@Column(name = "MEDI_NR_DIGITO")
	private Integer digito = 8;

//	@Column(name = "MEDI_NR_LEITURA_ACUMULADA")
//	private Integer leituraAcumulada;

	@Column(name = "MEDI_MD_PRESSAO_MAXIMA")
	private BigDecimal pressaoMaximaTrabalho = new BigDecimal(0.5);

//	@Column(name = "MEDI_COMPOSICAO_VIRTUAL")
//	private String composicaoVirtual;

//	@Column(name = "MEDI_CD_SUPERVISORIO")
//	private String codigoMedidorSupervisorio;

	@Column(name = "MEDI_MD_PRESSAO_ATUAL")
	private BigDecimal pressaoAtual = new BigDecimal(0);

	@Column(name = "MEDI_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "MEDI_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "MEDI_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public Medidor(Long id) {
		super(id);
		this.id = id;
	}

//	@Override
//	public String getDescricao() {
//		return null;
//	}
//
//	@Override
//	public void setDescricao(String descricao) {
//
//	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, MedidorDTO.class);
	}
}