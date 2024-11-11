package com.stfn2.ggas.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.ContratoDTO;
import com.stfn2.ggas.domain.enumTypes.ContratoSituacaoEnum;
import com.stfn2.ggas.domain.enumTypes.FormaCobrancaEnum;
import com.stfn2.ggas.domain.enumTypes.TipoVinculoContratoEnum;
import com.stfn2.ggas.domain.enumTypes.converter.ContratoSituacaoConverter;
import com.stfn2.ggas.domain.enumTypes.converter.FormaCobrancaConverter;
import com.stfn2.ggas.domain.enumTypes.converter.TipoVinculoContratoConverter;
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
@Table(name = "CONTRATO")
public class Contrato extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CONT_CD")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CONT")
    @SequenceGenerator(name = "SQ_CONT", sequenceName = "SQ_CONT_CD", allocationSize = 1)
    private Long id;

    @Column(name = "CONT_IN_USO")
    private Boolean habilitado = true;

    @Column(name = "CONT_NR_VERSAO")
    private Integer versao = 0;

    @Column(name = "CONT_TM_ULTIMA_ALTERACAO")
    private LocalDateTime ultimaAlteracao = LocalDateTime.now();

    @Column(name = "CONT_NR", nullable = false)
    private Integer numero;

    @Column(name = "CONT_DT_ANO", nullable = false)
    private Integer anoContrato;

    @Column(name = "CONT_DT_ASSINATURA")
    private LocalDate dataAssinatura;

    /*@Column(name = "CONT_NR_ANTERIOR")
        private String numeroAnterior;*/
    @Column(name = "CONT_NR_ADITIVO")
    private Integer numeroAditivo;

    /*@Column(name = "CONT_DT_ADITIVO")
        private LocalDate dataAditivo;*/
 /*@Column(name = "CONT_VL_GASTO_MENSAL")
        private BigDecimal valorGastoMensal;*/
 /*@Column(name = "CONT_MD_ECONOMIA_MENSAL_GN")
        private BigDecimal medidaEconomiaMensalGN;*/
 /*@Column(name = "CONT_MD_ECONOMIA_ANUAL_GN")
        private BigDecimal medidaEconomiaAnualGN;*/
    @Column(name = "CONT_IN_RENOVACAO_AUTOMATICA", nullable = false)
    private Boolean renovacaoAutomatica;

    @Column(name = "CONT_IN_PROPOSTA_APROVADA", nullable = false)
    private Boolean propostaAprovada = true;

    @Column(name = "CONT_IN_EXIGE_APROVACAO", nullable = false)
    private Boolean exigeAprovacao = false;

    @Column(name = "CONT_IN_ANO_CONTRATUAL", nullable = false)
    private Boolean indicadorAnoContratual = true;

    @Column(name = "ARCC_CD_IND_DEBITO_AUTOMATICO", nullable = false)
    private Boolean indicadorDebitoAutomatico;

    @Column(name = "CONT_IN_PENALIDADE_CIVIL", nullable = false)
    private Boolean tipoPeriodicidadePenalidade = true;

    /*@Column(name = "CONT_PR_ECONOMIA_GN")
        private BigDecimal percentualEconomiaGN;*/
 /*@Column(name = "CONT_NR_DIA_VENC_FINANCIAMENTO")
        private Integer numeroDiaVencimentoFinanciamento;*/
    @Column(name = "CONT_DT_FIM_GARANTIA_FINANC")
    private LocalDate dataFimGarantiaFinanciamento;

    @Column(name = "CONT_DT_INI_GARANTIA_FINANC")
    private LocalDate dataInicioGarantiaFinanciamento;

    @Column(name = "CONT_VL_GARANTIA_FINANCEIRA")
    private BigDecimal valorGarantiaFinanceira;

    @Column(name = "CONT_DS_GARANTIA_FINANCEIRA")
    private String descricaoGarantiaFinanceira;

    /*@Column(name = "CONT_IN_RENOV_GARANTIA_FINANC")
        private Boolean renovacaoGarantiaFinanceira;*/
    @Column(name = "CONT_NR_ANT_REV_GARANT_FINANC")
    private Integer diasAntecedenciaRevisaoGarantiaFinanceira;

    @Column(name = "CONT_NR_PERI_REAV_GARANT")
    private Integer periodicidadeReavaliacaoGarantias;

    @Column(name = "CONT_NR_DIAS_ANTECED_RENOVACAO")
    private Integer diasAntecedenciaRenovacao;

    /*@Column(name = "CONT_DS_ADITIVO")
        private String descricaoAditivo;*/
    @Column(name = "CONT_IN_AGRUPAMENTO_CONTA")
    private Boolean agrupamentoConta = false;

    @Column(name = "CONT_IN_AGRUPAMENTO_COBRANCA")
    private Boolean agrupamentoCobranca = false;

    /*@Column(name = "CONT_NR_EMPENHO")
        private String numeroEmpenho;*/
    @Column(name = "CONT_NR_DIAS_RENOVACAO_AUTOM")
    private Integer numeroDiasRenovacaoAutomatica;

    @Column(name = "CONT_DT_VENC_OBRIGACOES")
    private LocalDate dataVencimentoObrigacoes;

    @Column(name = "CONT_VL")
    private BigDecimal valorContrato;

    @Column(name = "CONT_IN_MULTA")
    private Boolean indicadorMulta = true;

    @Column(name = "CONT_IN_JUROS")
    private Boolean indicadorJuros = true;

    /*@Column(name = "CONT_VL_FINANCIAMENTO")
        private BigDecimal valorParticipacaoCliente;*/
 /*@Column(name = "CONT_NR_PARCELA_FINACIMENTO")
        private Integer qtdParcelasFinanciamento;*/
 /*@Column(name = "CONT_PR_JUROS")
        private BigDecimal percentualJurosFinanciamento;*/
 /*@Column(name = "CONT_PR_TARIFA_DOP")
        private BigDecimal percentualTarifaDoP;*/
    @Column(name = "CONT_IND_PARTICIPA_ECARTAS")
    private Boolean indicadorParticipanteECartas;

    /*@Column(name = "CONT_PR_TARIFA_GAS_FORA_ESPEC")
        private BigDecimal percentualSobreTariGas;*/
    @Column(name = "CONT_PR_MULTA")
    private BigDecimal percentualMulta; //valor padrão 0,02

    @Column(name = "CONT_PR_JUROS_MORA")
    private BigDecimal percentualJurosMora; //valor padrão 0,01

    //<property name="numeroCompletoContrato" formula="((CONT_DT_ANO * 100000) + CONT_NR)" type="string"/>
    //private String numeroCompletoContrato;
    /*@Column(name = "CONT_VL_INVESTIMENTO")
        private BigDecimal valorInvestimento;*/
 /*@Column(name = "CONT_DT_INVESTIMENTO")
        private LocalDate dataInvestimento;*/
 /*@Column(name = "CONT_IN_GERACAO_FATURA_ENC")
        private Boolean faturaEncerramentoGerada;*/
    @Column(name = "CONT_DT_RECISAO")
    private LocalDate dataRecisao;

    /*@Column(name = "CONT_DT_INICIO_RETIRADA_QPNR")
        private LocalDate dataInicioRetiradaQPNR;*/
 /*@Column(name = "CONT_DT_FIM_RETIRADA_QPNR")
        private LocalDate dataFimRetiradaQPNR;*/
 /*@Column(name = "CONT_PR_QDC_CONTRATO_QPNR")
        private BigDecimal percentualQDCContratoQPNR;*/
 /*@Column(name = "CONT_PR_MIN_QDC_CONTRATO_QPNR")
        private BigDecimal percentualMinimoQDCContratoQPNR;*/
 /*@Column(name = "CONT_PR_QDC_FIM_CONTRATO_QPNR")
        private BigDecimal percentualQDCFimContratoQPNR;*/
 /*@Column(name = "CONT_NR_ANOS_VALIDADE_QPNR")
        private Integer anosValidadeRetiradaQPNR;*/
    @Column(name = "CONT_CD_PAI")
    private Long chavePrimariaPai;

    @Column(name = "CONT_DS")
    private String descricaoContrato;

    @Column(name = "CONT_VL_VOLUME_REFERENCIA")
    private BigDecimal volumeReferencia;

    /*@Column(name = "CONT_NR_ORDEM_FATURAMENTO")
        private Integer ordemFaturamento;*/
    @Column(name = "CONT_CD_PRINCIPAL")
    private Long chavePrimariaPrincipal;

    @Column(name = "CONT_NR_CPG")
    private String numeroContratoCompagas;

    @Column(name = "CLIE_DEBITO_NR")
    private String numeroClienteDebito;

    /*private Boolean enderecoPadrao;*/
    @Column(name = "CONT_NR_AGENCIA", nullable = false)
    private String agencia;

    @Column(name = "CONT_NR_CONTA_CORRENTE", nullable = false)
    private String contaCorrente;

    @Column(name = "CONT_DT_LIBERACAO_GAS")
    private LocalDate dataLiberacaoGas;

    @Convert(converter = ContratoSituacaoConverter.class)
    @Column(name = "COSI_CD")
    private ContratoSituacaoEnum contratoSituacao;

    @ManyToOne
    @JoinColumn(name = "ENCO_CD_GARANTIA_FINANCEIRA")
    private EntidadeConteudo garantiaFinanceira;

    /*@ManyToOne
    @JoinColumn(name = "COME_CD")
    private ContratoModelo contratoModelo;*///Não mapear
    @Column(name = "COME_CD") 
    private Long contratoModelo;
    
    @Convert(converter = TipoVinculoContratoConverter.class)
    @Column(name = "CONT_TIPO_VINCULO_CONTRATO")
    private TipoVinculoContratoEnum tipoVinculoContratoEnum;
    
    @Column(name = "CONT_DATA_CRIACAO")
    private LocalDateTime dataCriacao = LocalDateTime.now();
    

    /*@ManyToOne
        @JoinColumn(name = "PROP_CD")
	private Proposta proposta;*///Todos os valores null
    @ManyToOne
    @JoinColumn(name = "CLIE_CD_ASSINATURA")
    private Cliente clienteAssinatura;

    @ManyToOne(fetch = FetchType.LAZY)//Eager igual o v1
    @JoinColumn(name = "INFI_CD")
    private IndiceFinanceiro indiceFinanceiro;//Valor Padrão 1

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ENCO_CD_TIPO_CALCULO_CORRECAO_MONETARIA")
    private EntidadeConteudo tipoCalculoCorrecaoMonetaria;

    //private EntidadeConteudo consumoReferenciaSobreDemanda;//Todos os valores null
    @OneToOne(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private ContratoPontoConsumo contratoPontoConsumo;

    /*@OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ContratoPenalidade> listaContratoPenalidade;*/ //Todos os valores null
    @OneToOne(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true)
    private ContratoCliente contratoCliente;

    @JsonManagedReference
    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL)
    List<ContratoCliente> listaContratoCliente = new ArrayList<>();

    /*@OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ContratoQDC> listaContratoQDC;*/ //Não Mapear

    /*@ManyToOne
        @JoinColumn(name = "ENCO_CD_AMORTIZACAO")
	private EntidadeConteudo sistemaAmortizacao;*///Todos os valores null   
    
    @Convert(converter = FormaCobrancaConverter.class)
    @Column(name = "ENCO_CD_TIPO_CONVENIO")    
    private FormaCobrancaEnum formaCobranca;

    @ManyToOne
    @JoinColumn(name = "ENCO_CD_TIPO_AGRUPAMENTO")
    private EntidadeConteudo tipoAgrupamento;//Todos os valores null

    //private EntidadeConteudo contratoCompra; Não mapeado hibernate
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ENCO_CD_MULTA_RECISORIA")
    private EntidadeConteudo multaRecisoria;

    @ManyToOne
    @JoinColumn(name = "ARCC_CD")
    private ArrecadadorContratoConvenio arrecadadorContratoConvenio;

    @ManyToOne
    @JoinColumn(name = "ARCC_CD_DEBITO_AUTOMATICO")
    private ArrecadadorContratoConvenio arrecadadorContratoConvenioDebitoAutomatico;

    /*@ManyToOne
        @JoinColumn(name = "ENCO_CD_PERI_MAIOR_MENOR")
	private EntidadeConteudo periodicidadeMaiorMenor;*///Todos os valores null
    @OneToMany(mappedBy = "contratoAtual", cascade = CascadeType.ALL, fetch = FetchType.LAZY)//Lazy exclusivo v2
    private List<Fatura> listaFatura;

    //private List<ServicoAutorizacao> listaServicoAutorizacao; Não mapeado hibernate
    /*@OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<ContratoAnexo> anexos;*/ //Todos os valores null
    @ManyToOne(fetch = FetchType.LAZY)//Lazy exclusivo v2
    @JoinColumn(name = "BANC_CD")
    private Banco banco;

    public Contrato(Long id) {
        super(id);
        this.id = id;
    }

    public String getNumeroFormatado() {

        StringBuilder numeroFormatado = new StringBuilder();
        if (this.getAnoContrato() != null) {
            numeroFormatado.append(this.getAnoContrato().toString());
        }
        numeroFormatado.append(String.format("%05d", this.getNumero()));
        numeroFormatado.append("-");
        numeroFormatado.append(String.format("%02d", this.getNumeroAditivo()));
        return numeroFormatado.toString();
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
        return MapperImpl.parseObject(this, ContratoDTO.class);
    }

    /*public BaseDTO convert() {
		return MapperImpl.parseObject(this, ContratoBasicDTO.class);
	}*/
    @Override
    public String getDescricaoField() {
        return "numero";
    }
}
