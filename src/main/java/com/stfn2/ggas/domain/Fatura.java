package com.stfn2.ggas.domain;

import com.stfn2.ggas.constante.Constantes;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.FaturaDTO;
import com.stfn2.ggas.domain.enumTypes.SituacaoPagamentoEnum;
import com.stfn2.ggas.domain.enumTypes.StatusFaturaEnum;
import com.stfn2.ggas.domain.enumTypes.converter.SituacaoPagamentoConverter;
import com.stfn2.ggas.domain.enumTypes.converter.StatusFaturaConverter;
import com.stfn2.ggas.tools.ToolDate;
import com.stfn2.ggas.tools.ToolNumber;
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
@Table(name = "FATURA")
public class Fatura extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 1L;

	private static final int NUMERO_DECIMAIS = 2;

	private static final int INICIO_CAMPO_MES = 4;

	private static final int LIMITE_CAMPO_ANO = 4;

	@Id
	@Column(name = "FATU_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FATU")
	@SequenceGenerator(name = "SQ_FATU", sequenceName = "SQ_FATU_CD", allocationSize = 1)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIE_CD", referencedColumnName = "CLIE_CD")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "POCN_CD", referencedColumnName = "POCN_CD")
	private PontoConsumo pontoConsumo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ROTA_CD", referencedColumnName = "ROTA_CD")
	private Rota rota;

	@ManyToOne
	@JoinColumn(name = "SEGM_CD", referencedColumnName = "SEGM_CD")
	private Segmento segmento;

	@Column(name = "ENCO_CD_SITUACAO_PAGAMENTO")
	@Convert(converter = SituacaoPagamentoConverter.class)
	private SituacaoPagamentoEnum situacaoPagamento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ENCO_CD_MOTIVO_INCLUSAO", referencedColumnName = "ENCO_CD")
	private EntidadeConteudo motivoInclusao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ENCO_CD_MOTIVO_CANCELAMENTO", referencedColumnName = "ENCO_CD")
	private EntidadeConteudo motivoCancelamento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ENCO_CD_MOTIVO_COMPLEMENTO", referencedColumnName = "ENCO_CD")
	private EntidadeConteudo motivoCompletamento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ENCO_CD_MOTIVO_REFATURAMENTO", referencedColumnName = "ENCO_CD")
	private EntidadeConteudo motivoRefaturamento;

	@Column(name = "ENCO_CD_STATUS_FATURA")
	@Convert(converter = StatusFaturaConverter.class)
	private StatusFaturaEnum statusFatura;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FATU_CD_AGRUPADA", referencedColumnName = "FATU_CD")
	private Fatura faturaAgrupadora;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FATU_CD_PRINCIPAL", referencedColumnName = "FATU_CD")
	private Fatura faturaPrincipal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FAGE_CD", referencedColumnName = "FAGE_CD")
	private FaturaGeral faturaGeral;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CRDS_CD", referencedColumnName = "CRDS_CD")
	private CreditoDebitoSituacao creditoDebitoSituacao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NAOC_CD", referencedColumnName = "NAOC_CD")
	private NaturezaOperacaoCfop naturezaOperacaoCfop;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FAMR_CD", referencedColumnName = "FAMR_CD")
	private FaturaMotivoRevisao faturaMotivoRevisao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DOTI_CD", referencedColumnName = "DOTI_CD")
	private DocumentoTipo documentoTipo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEVO_CD", referencedColumnName = "DEVO_CD")
	private Devolucao devolucao;
        
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CONT_CD_PAI", referencedColumnName = "CONT_CD")
	private Contrato contratoPai;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CONT_CD_ATUAL", referencedColumnName = "CONT_CD")
	private Contrato contratoAtual;

	@ManyToOne
	@JoinColumn(name = "MEHI_CD", referencedColumnName = "MEHI_CD")
	private MedicaoHistorico medicaoHistorico;

	@ManyToOne
	@JoinColumn(name = "COHI_CD", referencedColumnName = "COHI_CD")
	private ConsumoHistorico consumoHistorico;

	/*
	@ManyToOne
	@JoinColumn(name = "CADV_CD", referencedColumnName = "CADV_CD")
	private CampanhaDescontoVincular campanhaDescontoVincular;

	@ManyToOne
	@JoinColumn(name = "PARC_CD", referencedColumnName = "PARC_CD")
	private Parcelameto parcelamento;
	*/

	@OneToMany(mappedBy = "fatura", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FaturaItem> listaFaturaItens = new ArrayList<>();

	@OneToMany(mappedBy = "fatura", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FaturaTributacao> faturaTributacao = new ArrayList<>();

	@OneToMany(mappedBy = "fatura", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FaturaImpressao> faturaImpressao = new ArrayList<>();

	@OneToMany(mappedBy = "faturaAtual", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FaturaGeral> faturaAtual = new ArrayList<>();

	@OneToMany(mappedBy = "fatura", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DocumentoFiscal> documentoFiscal = new ArrayList<>();

	@Column(name = "FATU_DT_AM_REFERENCIA")
	private Integer anoMes;

	@Column(name = "BOLETO_CD")
	private Long boleto;

	@Column(name = "FATU_VR_DIFERIMENTO")
	private BigDecimal valorDiferimento;

	@Column(name = "FATU_VL_CONCILIADO")
	private BigDecimal valorConciliado;

	@Column(name = "FATU_VL_TOTAL")
	private BigDecimal valorTotal;

	@Column(name = "FATU_DT_EMISSAO")
	private LocalDate dataEmissao;

	@Column(name = "FATU_DT_VENCIMENTO")
	private LocalDate dataVencimento;

	@Column(name = "FATU_DT_REVISAO")
	private LocalDate dataRevisao;

	@Column(name = "FATU_DT_CANCELAMENTO")
	private LocalDate dataCancelamento;

	@Column(name = "FATU_DS_MOTIVO_CANCELA_NOTA")
	private String motivoCancelamentoNota;

	@Column(name = "FATU_DS_MOTIVO_ALTER_VENC")
	private String motivoAlteracaoVencimento;

	@Column(name = "FATU_DS_OBSERVACAO_NOTA")
	private String observacaoNota;

	@Column(name = "FATU_DS_COMPLEMENTO")
	private String observacaoFaturaComplementar;

	@Column(name = "FATU_PERIODO_CICLO")
	private String periodoCiclo;

	@Column(name = "FATU_NR")
	private Integer numeroFatura;

	@Column(name = "FATU_NR_CICLO")
	private Integer ciclo;

	@Column(name = "FATU_MD_PRESSAO_ATUAL")
	private BigDecimal pressaoAtual;

	@Column(name = "FATU_IN_DENEGADA")
	private Boolean denegada;

	@Column(name = "FATU_IN_ENCERRAMENTO")
	private Boolean encerramento;

	@Column(name = "FATU_IN_REGISTRO_PERDA")
	private Boolean registroPerda;

	@Column(name = "FATU_IN_NOTIFICACAO")
	private Boolean notificacao;

	@Column(name = "FATU_IN_COBRADA")
	private Boolean cobrada;

	@Column(name = "FATU_IN_PDD")
	private Boolean pdd;

	@Column(name = "FATU_IN_AVULSO")
	private Boolean avulso;

	@Column(name = "FATU_IN_EXIBIR_AVISO_CORTE")
	private Boolean exibirAvisoCorte;

	@Column(name = "CLI_LOGRADOURO")
	private String clienteLogradouro;

	@Column(name = "CLI_NUM_IMOV")
	private String clienteNumeroImovel;

	@Column(name = "CLI_COMPL_END")
	private String clienteComplementoEndereco;

	@Column(name = "CLI_BAIRRO")
	private String clienteBairro;

	@Column(name = "CLI_CEP")
	private String clienteCep;

	@Column(name = "CLI_MUNICIPIO")
	private String clienteMunicipio;

	@Column(name = "CLI_UF")
	private String clienteUF;

	@Column(name = "FATU_DOC_ID_CLIE")
	private String clienteCpfOrCNPJ;

	@Column(name = "FATU_IE_CLIE")
	private String clienteInscricaoEstadual;
	
	@Column(name = "FATU_IN_USO")
	private Boolean habilitado = true;

	@Column(name = "FATU_NR_VERSAO")
	private Integer versao = 0;

	@Column(name = "FATU_TM_ULTIMA_ALTERACAO")
	private LocalDateTime ultimaAlteracao = LocalDateTime.now();

	public Fatura(Long id) {
		super(id);
		this.id = id;
	}

	public String getCicloReferenciaFormatado() {
		String referencia = null;
		String ano = String.valueOf(anoMes).substring(0, LIMITE_CAMPO_ANO);
		String mes = String.valueOf(anoMes).substring(INICIO_CAMPO_MES, String.valueOf(anoMes).length());
		if (ciclo == null) {
			referencia = "";
		} else {
			referencia = mes + "/" + ano + "-" + ciclo;
		}
		return referencia;
	}

	public String getValorTotalFormatado() {
		return ToolNumber.converterCampoValorDecimalParaString("", this.valorTotal, Constantes.LOCALE_PADRAO,
				NUMERO_DECIMAIS);
	}

	public String getDataEmissaoFormatada() {
		return ToolDate.converterDataParaStringSemHora(this.dataEmissao, Constantes.FORMATO_DATA_BR);
	}

	public String getDataVencimentoFormatada() {
		return ToolDate.converterDataParaStringSemHora(this.dataVencimento, Constantes.FORMATO_DATA_BR);
	}

	public boolean isVencida() {
		return ToolDate.antesHoje(getDataVencimento());
	}

	@Override
	public String getDescricao() {
		if(this.numeroFatura != null){
			return this.numeroFatura.toString();
		}
		return this.id.toString();
	}

	@Override
	public void setDescricao(String descricao) {
	}

	@Override
	public BaseDTO convert() {
		return MapperImpl.parseObject(this, FaturaDTO.class);
	}
}
