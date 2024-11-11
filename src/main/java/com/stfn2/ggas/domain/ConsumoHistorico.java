package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.ConsumoHistoricoDTO;
import com.stfn2.ggas.domain.enumTypes.PCSIntervaloEnum;
import com.stfn2.ggas.domain.enumTypes.converter.PCSIntervaloConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CONSUMO_HISTORICO")
public class ConsumoHistorico extends BaseEntity {
   @Serial
   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "COHI_CD")
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_COHI")
   @SequenceGenerator(name = "SQ_COHI", sequenceName = "SQ_COHI_CD", allocationSize = 1)
   private Long id;

   @Column(name = "COHI_IN_USO")
   private Boolean habilitado = true;

   @Column(name = "COHI_NR_VERSAO")
   private Integer versao = 0;

   @Column(name = "COHI_TM_ULTIMA_ALTERACAO")
   private LocalDateTime ultimaAlteracao = LocalDateTime.now();

   @Transient
   private String descricao;

   @Column(name = "COHI_DT_AM_FATURAMENTO", nullable = false)
   private Integer anoMesFaturamento;

   @Column(name = "COHI_SQ")
   private Integer sequencia;

   @Column(name = "COHI_NR_CICLO")
   private Integer numeroCiclo;

   @Column(name = "COHI_MD_CONSUMO")
   private BigDecimal consumo;

   @Column(name = "COHI_MD_CONSUMO_MEDIDO")
   private BigDecimal consumoMedido;

   @Column(name = "COHI_MD_CONSUMO_APURADO")
   private BigDecimal consumoApurado;

   @Column(name = "COHI_IN_IMOVEL_CONDOMINIO")
   private Boolean indicadorImovelCondominio;

   @Column(name = "COHI_MD_CONSUMO_MEDIO")
   private BigDecimal consumoMedio;

   @Column(name = "COHI_MD_CONSUMO_MINIMO")
   private BigDecimal consumoMinimo;

   @Column(name = "COHI_MD_CONSUMO_IMOVEL_VINC")
   private BigDecimal consumoImovelVinculado;

   @Column(name = "COHI_MD_CONSUMO_CREDITO")
   private BigDecimal consumoCredito;

   @Column(name = "COHI_MD_CONSUMO_CREDIT_MEDIA")
   private BigDecimal consumoCreditoMedia;

   @Column(name = "COHI_IN_FATURAMENTO")
   private Boolean indicadorFaturamento;

   @Column(name = "COHI_QN_DIAS_CONSUMO")
   private Integer diasConsumo;

   @Column(name = "COHI_MD_CONSUMO_APURADO_MEDIO")
   private BigDecimal consumoApuradoMedio;

   @Column(name = "COHI_MD_PCS")
   private BigDecimal medidaPCS;

   @Column(name = "COHI_MD_Z")
   private BigDecimal medidaZ;

   @Column(name = "COHI_NR_FATOR_PCS")
   private BigDecimal fatorPCS;

   @Column(name = "COHI_NR_FATOR_PTZ")
   private BigDecimal fatorPTZ;

   @Column(name = "COHI_NR_FATOR_CORRECAO")
   private BigDecimal fatorCorrecao;

   @Column(name = "COHI_IN_CONSUMO_CICLO")
   private Boolean indicadorConsumoCiclo;


   //private BigDecimal variacaoConsumo;

   @Column(name = "COHI_IN_DRAWBACK")
   private Boolean indicadorDrawback;

   @Column(name = "COHI_COMPOSICAO_VIRTUAL")
   private String composicaoVirtual;

   @Column(name = "COHI_IN_MEDICAO_IND_ATUAL")
   private Boolean indicadorMedicaoIndependenteAtual;

   @ManyToOne
   @JoinColumn(name = "COHI_CD_CONSUMO_CONDOMINIO")
   private ConsumoHistorico consumoCondominio;

   @ManyToOne
   @JoinColumn(name = "MEHI_CD_ANTERIOR")
   private MedicaoHistorico historicoAnterior;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "MEHI_CD_ATUAL")
   private MedicaoHistorico historicoAtual;

   @ManyToOne
   @JoinColumn(name = "POCN_CD", nullable = false)
   private PontoConsumo pontoConsumo;

  /* @Convert(converter = ConsumoTipoConverter.class)
   @JoinColumn(name = "COTP_CD", nullable = false)
   private ConsumoTipoEnum consumoTipo;*/

   @ManyToOne
   @JoinColumn(name = "COAN_CD")
   private ConsumoAnormalidade consumoAnormalidade;

   @Convert(converter = PCSIntervaloConverter.class)
   @Column(name = "PCIN_CD", nullable = false)
   private PCSIntervaloEnum pcsIntervalo;

   @ManyToOne
   @JoinColumn(name = "ENCO_CD_LOCAL_AMOSTRAGEM_PCS")
   private EntidadeConteudo localAmostragemPCS;

   @ManyToOne
   @JoinColumn(name = "COHI_CD_SINTETIZADOR")
   private ConsumoHistorico historicoConsumoSintetizador;

   @ManyToOne
   @JoinColumn(name = "MEDI_CD", nullable = false)
   private Medidor medidor;

   public ConsumoHistorico(Long id) {
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
      return MapperImpl.parseObject(this, ConsumoHistoricoDTO.class);
   }
}