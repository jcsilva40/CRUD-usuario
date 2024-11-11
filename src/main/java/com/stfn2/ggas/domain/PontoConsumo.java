package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.PontoConsumoDTO;
import com.stfn2.ggas.domain.enumTypes.PontoConsumoSituacaoEnum;
import com.stfn2.ggas.domain.enumTypes.converter.PontoConsumoSituacaoConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PONTO_CONSUMO")
public class PontoConsumo extends BaseEntity {
   @Serial
   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "POCN_CD")
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_POCN")
   @SequenceGenerator(name = "SQ_POCN", sequenceName = "SQ_POCN_CD", allocationSize = 1)
   private Long id;


   @Column(name = "POCN_DS")
   private String descricao;

   @Column(name = "POCN_CD_LEGADO")
   private String codigoLegado;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "CEP_CD", referencedColumnName = "CEP_CD")
   private Cep cepTabela;//Usado apenas para retornar endereco

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "RAAT_CD", referencedColumnName = "RAAT_CD")
   private RamoAtividade ramoAtividade;

   @Column(name = "QUFA_CD")
   private Long quadraFace;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "SEGM_CD", referencedColumnName = "SEGM_CD")
   private Segmento segmento;

   @Column(name = "POCS_CD")
   @Convert(converter = PontoConsumoSituacaoConverter.class)
   private PontoConsumoSituacaoEnum pontoConsumoSituacao;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "ROTA_CD", referencedColumnName = "ROTA_CD")
   private Rota rota;

   @ManyToOne
   @JoinColumn(name = "IMOV_CD", referencedColumnName = "IMOV_CD")
   private Imovel imovel;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "MEIN_CD", referencedColumnName = "MEIN_CD")
   private MedidorInstalacao medidorInstalacao;

   @OneToMany(mappedBy = "pontoConsumo", fetch = FetchType.LAZY)
   private List<MedidorInstalacao> medidores = new ArrayList<>();

   @Column(name = "POCN_NR_CIL")
   private String complementoCil;

   @Column(name = "POCN_MSG_FATURA")
   private String mensagemFatura;

   @Column(name = "POCN_FATOR_CORRECAO")
   private BigDecimal fatorCorrecao;

   @Column(name = "POCN_OBSERVACAO")
   private String observacao;

   @Column(name = "POCN_CD_SUPERVISORIO")
   private String cil;

   @Column(name = "POCN_DS_ENDERECO_REFERENCIA")
   private String logradouro;

   @Column(name = "POCN_DS_COMPLEMENTO")
   private String complemento;

   @Column(name = "POCN_NR_IMOVEL")
   private String numero;

   @Column(name = "POCN_DS_BAIRRO")
   private String bairro;

   @Column(name = "POCN_DS_CEP")
   private String cep;

   @Column(name = "POCN_DS_MUNICIPIO")
   private String localidade;

   @Column(name = "POCN_DS_UF")
   private String uf;

   @Column(name = "POCN_NR_SEQUENCIA")
   private Integer numeroSequencia;

   @Column(name = "POCN_NR_SEQUENCIA_LEITURA")
   private Integer sequenciaLeitura;

   @Column(name = "POCN_IN_USO")
   private Boolean habilitado;

   @Column(name = "POCN_NR_VERSAO")
   private Integer versao = 0;

   @Column(name = "POCN_TM_ULTIMA_ALTERACAO")
   private LocalDateTime ultimaAlteracao = LocalDateTime.now();
   
    @OneToMany(mappedBy = "pontoConsumo", fetch = FetchType.LAZY)
    private List<ContratoPontoConsumo> listaContratoPontoConsumo = new ArrayList<>();

   public PontoConsumo(Long id) {
      super(id);
      this.id = id;
   }

   @Override
   public BaseDTO convert() {
      return MapperImpl.parseObject(this, PontoConsumoDTO.class);
   }
}
