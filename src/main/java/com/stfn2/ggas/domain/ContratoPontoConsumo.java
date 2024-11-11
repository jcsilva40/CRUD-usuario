package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.ContratoPontoConsumoDTO;
import com.stfn2.ggas.domain.enumTypes.AcaoAnormalidadeConsumoEnum;
import com.stfn2.ggas.domain.enumTypes.MedicaoTipoEnum;
import com.stfn2.ggas.domain.enumTypes.converter.AcaoAnormalidadeConsumoConverter;
import com.stfn2.ggas.domain.enumTypes.converter.MedicaoTipoConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CONTRATO_PONTO_CONSUMO")
public class ContratoPontoConsumo extends BaseEntity {
   @Serial
   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "COPC_CD")
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_COPC")
   @SequenceGenerator(name = "SQ_COPC", sequenceName = "SQ_COPC_CD", allocationSize = 1)
   private Long id;
	
	/*@Column(name = "COPC_IN_USO")
	private Boolean habilitado = true;*/ //Não tem na tabela

   @Column(name = "COPC_NR_VERSAO")
   private Integer versao = 0;

   @Column(name = "COPC_TM_ULTIMA_ALTERACAO")
   private LocalDateTime ultimaAlteracao = LocalDateTime.now();

   @Column(name = "COPC_MD_PRESSAO")
   private BigDecimal medidaPressao; //Valor padrão 4.1
        
        /*@Column(name = "COPC_MD_PRESSAO_COLETADA")
        private BigDecimal medidaPressaoColetada;*/ //Todos os valores null

   @Column(name = "COPC_MD_PRESSAO_MINIMA")
   private BigDecimal medidaPressaoMinima;

   @Column(name = "COPC_MD_PRESSAO_MAXIMA")
   private BigDecimal medidaPressaoMaxima;
        
        /*@Column(name = "COPC_MD_PRESSAO_LIMITE")
        private BigDecimal medidaPressaoLimite;*/ //Todos os valores null

   @Column(name = "COPC_MD_VAZAO_MAX_INSTANTANEA")
   private BigDecimal medidaVazaoInstantanea;

   @Column(name = "COPC_MD_VAZAO_INSTANTANEA")
   private BigDecimal medidaVazaoMaximaInstantanea;
        
        /*@Column(name = "COPC_MD_VAZAO_MIN_INSTANTANEA")
        private BigDecimal medidaVazaoMinimaInstantanea;*/ //Todos os valores null

   @Column(name = "COPC_MD_FORNEC_MAX_DIARIO")
   private BigDecimal medidaFornecimentoMaxDiaria;

   @Column(name = "COPC_MD_FORNEC_MIN_DIARIO")
   private BigDecimal medidaFornecimentoMinDiaria;

   @Column(name = "COPC_MD_FORNEC_MIN_MENSAL")
   private BigDecimal medidaFornecimentoMinMensal;

   @Column(name = "COPC_MD_FORNEC_MIN_ANUAL")
   private BigDecimal medidaFornecimentoMinAnual;

   @Column(name = "COPC_NR_FATOR_CORRECAO")
   private BigDecimal numeroFatorCorrecao; //Valor padrão 2, podendo ser null
        
        /*@Column(name = "COPC_NR_DIA_VENCIMENTO")
        private Integer numeroDiasVencimento;*/ //Todos os valores null

   @Column(name = "COPC_IN_PARADA_PROGRAMADA")
   private Boolean indicadorParadaProgramada; //Valor padrão false, podendo ser true

   @Column(name = "COPC_QN_ANOS_PARADA_CLIE")
   private Integer quantidadeAnosParadaCliente;

   @Column(name = "COPC_QN_TOTAL_PARADA_CLIE")
   private Integer quantidadeTotalParadaCliente;

   @Column(name = "COPC_QN_MAX_ANO_PARADA_CLIE")
   private Integer quantidadeMaximaAnoParadaCliente;

   @Column(name = "COPC_QN_DIAS_ANTEC_PARADA_CLIE")
   private Integer diasAntecedentesParadaCliente;

   @Column(name = "COPC_QN_DIA_CONSEC_PARADA_CLIE")
   private Integer diasConsecutivosParadaCliente;

   @Column(name = "COPC_QN_ANOS_PARADA_CDL")
   private Integer quantidadeAnosParadaCDL;

   @Column(name = "COPC_QN_TOTAL_PARADA_CDL")
   private Integer quantidadeTotalParadaCDL;

   @Column(name = "COPC_QN_MAX_ANO_PARADA_CDL")
   private Integer quantidadeMaximaAnoParadaCDL;

   @Column(name = "COPC_QN_DIAS_ANTEC_PARADA_CDL")
   private Integer diasAntecedentesParadaCDL;
        
    @Column(name = "COPC_QN_DIA_CONSEC_PARADA_CDL")
    private Integer diasConsecutivosParadaCDL; //Todos os valores null

   @Column(name = "COPC_DT_INICIO_TESTE")
   private LocalDate dataInicioTeste;

   @Column(name = "COPC_DT_FIM_TESTE")
   private LocalDate dataFimTeste;
   
   @ManyToOne(fetch = FetchType.LAZY)//Lazy exclusivo v2
   @JoinColumn(name = "CEP_CD_ENTREGA")
   private Cep cep;

   @Column(name = "COPC_NR_IMOVEL")
   private String numeroImovel;   

   @Column(name = "COPC_DS_COMPLEMENTO_ENDERECO")
   private String complementoEndereco;
   
   @Column(name = "COPC_IN_FATURA_FISICA")
   private Boolean envioFaturaFisica;
   
   @Column(name = "COPC_DS_LOGRADOURO_FATURA")
   private String logradouroFatura;
           
   @Column(name = "COPC_NR_IMOVEL_FATURA")
   private String numeroImovelFatura;
           
   @Column(name = "COPC_DS_COMPLEMENTO_FATURA")
   private String complementoFatura;        
           
   @Column(name = "COPC_DS_BAIRRO_FATURA")
   private String bairroFatura;
           
   @Column(name = "COPC_DS_CEP_FATURA")
   private String cepFatura;      
           
   @Column(name = "COPC_DS_MUNICIPIO_FATURA")
   private String municipioFatura;
           
   @Column(name = "COPC_DS_UF_FATURA")
   private String ufFatura;

   @Column(name = "COPC_DS_EMAIL_ENTREGA")
   private String emailEntrega;
        
        /*@Column(name = "COPC_IN_CORRETOR_VAZAO")
        private Boolean indicadorCorretorVazao;*/ //Todos os valores null
        
        /*@Column(name = "COPC_MD_CONSUMO_TESTE")
        private BigDecimal medidaConsumoTeste;*/ //Todos os valores null
        
        /*@Column(name = "COPC_NR_PRAZO_CONSUMO_TESTE")
        private Integer prazoConsumoTeste;*/ //Todos os valores null
        
        /*@Column(name = "COPC_NR_DIA_GARANTIA_CONVERSAO")
        private Integer diaGarantiaConversao;*/ //Todos os valores null
        
        /*@Column(name = "COPC_DT_INI_GARANTIA_CONVERSAO")
        private LocalDate dataInicioGarantiaConversao;*/ //Todos os valores null
        
        /*@Column(name = "COPC_DS_EMAIL_OPERACIONAL")
        private String emailOperacional;*/ //Todos os valores null
        
        /*@Column(name = "COPC_CD_DDD_FAX_OPERACIONAL")
        private Integer codigoDDDFaxOperacional;*/ //Todos os valores null
        
        /*@Column(name = "COPC_NR_FAX_OPERACIONAL")
        private Integer numeroFaxOperacional;*/ //Todos os valores null
        
        /*@Column(name = "COPC_DT_CONSUMO_TESTE_EXCEDIDO")
        private LocalDate dataConsumoTesteExcedido;*/ //Todos os valores null
        
        /*@Column(name = "COPC_MD_CONSUMO_TESTE_USADO")
        private BigDecimal medidaConsumoTesteUsado;*/ //Todos os valores null

   @Column(name = "COPC_IN_EMISSAO_NFE")
   private Boolean emiteNotaFiscalEletronica; //Valor padrão true, mas pode ser false

   @Column(name = "COPC_NR_HORA_INICAL")
   private Integer numeroHoraInicial;

   @Column(name = "COPC_IN_EMITIR_FAT_NFE")
   private Boolean emitirFaturaComNfe;
               
        /*@ManyToOne(optional = false)
        @JoinColumn(name = "UNID_CD_PRESSAO_COLETADA")
	private Unidade unidadePressaoColetada;*/ //Todos os valores null

   @ManyToOne(optional = false, fetch = FetchType.LAZY)
   @JoinColumn(name = "UNID_CD_PRESSAO")
   private Unidade unidadePressao; //Valor padrão 42

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "UNID_CD_PRESSAO_MINIMA")
   private Unidade unidadePressaoMinima;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "UNID_CD_PRESSAO_MAXIMA")
   private Unidade unidadePressaoMaxima;

        /*@ManyToOne
        @JoinColumn(name = "UNID_CD_PRESSAO_LIMITE")
	private Unidade unidadePressaoLimite;*/ //Todos os valores null        	

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "UNID_CD_VAZAO_INSTANTANEA")
   private Unidade unidadeVazaoInstantanea;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "UNID_CD_VAZAO_MAX_INSTANTANEA")
   private Unidade unidadeVazaoMaximaInstantanea;

        /*@ManyToOne
        @JoinColumn(name = "UNID_CD_VAZAO_MIN_INSTANTANEA")
	private Unidade unidadeVazaoMinimaInstantanea;*/ //Todos os valores null	

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "UNID_CD_FORNEC_MAX_DIARIO")
   private Unidade unidadeFornecimentoMaxDiaria;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "UNID_CD_FORNEC_MIN_DIARIO")
   private Unidade unidadeFornecimentoMinDiaria;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "UNID_CD_FORNEC_MIN_MENSAL")
   private Unidade unidadeFornecimentoMinMensal;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "UNID_CD_FORNEC_MIN_ANUAL")
   private Unidade unidadeFornecimentoMinAnual;

   @ManyToOne(optional = false)
   @JoinColumn(name = "POCN_CD")
   private PontoConsumo pontoConsumo;

   @OneToOne(optional = false)
   @JoinColumn(name = "CONT_CD")
   private Contrato contrato;

   @Convert(converter = AcaoAnormalidadeConsumoConverter.class)
   @Column(name = "LEAC_CD_SEM_LEITURA")
   private AcaoAnormalidadeConsumoEnum acaoAnormalidadeConsumoSemLeitura;

   @Convert(converter = MedicaoTipoConverter.class)
   @Column(name = "METO_CD")
   private MedicaoTipoEnum medicaoTipo;//Valor padrão 1, podendo ser 2

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "ENCO_CD_REGIME_CONSUMO")
   private EntidadeConteudo regimeConsumo; //Valor padrão 14, podendo ser null

   

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "ENCO_CD_CONTRATO_COMPRA")
   private EntidadeConteudo contratoCompra; //Valor padrão 104

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "PERI_CD")
   private Periodicidade periodicidade;

   @ManyToOne(fetch = FetchType.LAZY)//Lazy exclusivo v2
   @JoinColumn(name = "PRFF_CD")
   private PressaoFornecimentoFaixa pressaoFornecimentoFaixa;

        /*@OneToMany(mappedBy = "contratoPontoConsumo", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ContratoPontoConsumoModalidade> listaContratoPontoConsumoModalidade;*/ //Não mapear

   @OneToMany(mappedBy = "contratoPontoConsumo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//Lazy exclusivo v2
   private List<ContratoPontoConsumoItemFaturamento> listaContratoPontoConsumoItemFaturamento;

   /*@OneToMany(mappedBy = "contratoPontoConsumo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
   //Lazy exclusivo v2
   private List<ContratoPontoConsumoPCSAmostragem> listaContratoPontoConsumoPCSAmostragem;*/
   @OneToOne(mappedBy = "contratoPontoConsumo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
   private ContratoPontoConsumoPCSAmostragem contratoPontoConsumoPCSAmostragem;
   

   /*@OneToMany(mappedBy = "contratoPontoConsumo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//Lazy exclusivo v2
   private List<ContratoPontoConsumoPCSIntervalo> listaContratoPontoConsumoPCSIntervalo;*/
   @OneToOne(mappedBy = "contratoPontoConsumo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
   private ContratoPontoConsumoPCSIntervalo contratoPontoConsumoPCSIntervalo;

   public ContratoPontoConsumo(Long id) {
      super(id);
      this.id = id;
   }

   public String getEnderecoFormatado() {

      StringBuilder enderecoFormatado = new StringBuilder();
      String separador = "";

      if (this.getCep() != null && this.getCep().getTipoLogradouro() != null) {
         enderecoFormatado.append(this.getCep().getTipoLogradouro());
      }

      if (this.getCep() != null && this.getCep().getLogradouro() != null) {

         if (enderecoFormatado.length() > 0) {
            separador = " ";
         } else {
            separador = "";
         }
         enderecoFormatado.append(separador);
         enderecoFormatado.append(this.getCep().getLogradouro());
      }

      if (this.getNumeroImovel() != null) {

         if (enderecoFormatado.length() > 0) {
            separador = ", ";
         } else {
            separador = "";
         }

         enderecoFormatado.append(separador);
         enderecoFormatado.append(this.getNumeroImovel());
      }

      if (this.getCep() != null && this.getCep().getBairro() != null) {

         if (enderecoFormatado.length() > 0) {
            separador = ", ";
         } else {
            separador = "";
         }

         enderecoFormatado.append(separador);
         enderecoFormatado.append(this.getCep().getBairro());
      }

      if (this.getCep() != null && this.getCep().getMunicipio() != null) {

         if (enderecoFormatado.length() > 0) {
            separador = ", ";
         } else {
            separador = "";
         }

         enderecoFormatado.append(separador);
         enderecoFormatado.append(this.getCep().getMunicipio().getDescricao());
      }

      return enderecoFormatado.toString();
   }

   public String getEnderecoFormatadoRuaNumeroComplemento() {

      StringBuilder enderecoFormatado = new StringBuilder();
      String separador = "";

      if (this.getCep().getLogradouro() != null) {
         enderecoFormatado.append(this.getCep().getLogradouro());
      }
      if (this.getNumeroImovel() != null) {
         if(enderecoFormatado.length() > 0){
            separador = ", ";
         }else{
            separador = "";
         }
         enderecoFormatado.append(separador);
         enderecoFormatado.append(this.getNumeroImovel());
      }
      if (this.getComplementoEndereco() != null) {
         if(enderecoFormatado.length() > 0){
            separador = ", ";
         }else{
            separador = "";
         }
         enderecoFormatado.append(separador);
         enderecoFormatado.append(this.getComplementoEndereco());
      }

      return enderecoFormatado.toString();
   }

   public String getEnderecoFormatadoRuaNumeroBairro() {

      StringBuilder enderecoFormatado = new StringBuilder();
      String separador = "";

      if (this.getCep().getLogradouro() != null) {
         enderecoFormatado.append(this.getCep().getLogradouro());
      }
      if (this.getNumeroImovel() != null) {
         if(enderecoFormatado.length() > 0){
            separador = ", ";
         }else{
            separador = "";
         }
         enderecoFormatado.append(separador);
         enderecoFormatado.append(this.getNumeroImovel());
      }

      if (this.getCep().getBairro() != null) {

         if (enderecoFormatado.length() > 0) {
            separador = ", ";
         } else {
            separador = "";
         }

         enderecoFormatado.append(separador);
         if (this.getCep().getBairro() != null && !this.getCep().getBairro().equals("Não Informado")) {

            if (enderecoFormatado.length() > 0) {
               separador = ", ";
            } else {
               separador = "";
            }

            enderecoFormatado.append(separador);
            enderecoFormatado.append(this.getCep().getBairro());
         }
      }

      return enderecoFormatado.toString();
   }

   public String getEnderecoFormatadoMunicipioUF() {

      StringBuilder enderecoFormatado = new StringBuilder();
      String separador = "";

      if (this.getCep().getMunicipio() != null) {
         enderecoFormatado.append(this.getCep().getMunicipio().getDescricao());
      }
      if (this.getCep().getUf() != null) {
         if(enderecoFormatado.length() > 0){
            separador = " - ";
         }else{
            separador = "";
         }
         enderecoFormatado.append(separador);
         enderecoFormatado.append(this.getCep().getUf());
      }

      return enderecoFormatado.toString();
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
      return MapperImpl.parseObject(this, ContratoPontoConsumoDTO.class);
   }

   @Override
   public Boolean getHabilitado() {
      return null;
   }
}