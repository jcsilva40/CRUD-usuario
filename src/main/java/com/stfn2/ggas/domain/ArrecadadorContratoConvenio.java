package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.ArrecadadorContratoConvenioDTO;
import com.stfn2.ggas.domain.enumTypes.TipoConvenioBancarioEnum;
import com.stfn2.ggas.domain.enumTypes.converter.TipoConvenioBancarioConverter;
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
@Table(name = "ARRECADADOR_CONTRATO_CONVENIO")
public class ArrecadadorContratoConvenio extends BaseEntity {
   @Serial
   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "ARCC_CD")
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ARCC")
   @SequenceGenerator(name = "SQ_ARCC", sequenceName = "SQ_ARCC_CD", allocationSize = 1)
   private Long id;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "COBA_CD_CONVENIO")
   private ContaBancaria contaConvenio;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "COBA_CD_CREDITO")
   private ContaBancaria contaCredito;

   @ManyToOne
   @JoinColumn(name = "ARCA_CD", referencedColumnName = "ARCA_CD")
   private ArrecadadorCarteiraCobranca arrecadadorCarteiraCobranca;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "DOLA_CD")
   private DocumentoLayout leiaute;

   @ManyToOne
   @JoinColumn(name = "ARCO_CD", referencedColumnName = "ARCO_CD")
   private ArrecadadorContrato arrecadadorContrato;

   @Column(name = "ENCO_CD_TIPO_CONVENIO")
   @Convert(converter = TipoConvenioBancarioConverter.class)
   private TipoConvenioBancarioEnum tipoConvenio;

   @Column(name = "ARCC_DS_LAYOUT_FATURA")
   private String arquivoLayoutFatura;

   /*@Column(name = "ARCC_NR_DIAS_FLOAT")
   private Integer numeroDiasFloat;*/ //Todos os valores null

   @Column(name = "ARCC_NR_NSA_REMESSA")
   private Long nsaRemessa;

   @Column(name = "ARCC_NR_NSA_RETORNO")
   private Long nsaRetorno;

   @Column(name = "ARCC_NR_SEQ_COBRANCA_INICIO")
   private Long sequencialCobrancaInicio; //Valor padrão 1

   @Column(name = "ARCC_NR_SEQ_COBRANCA_FIM")
   private Long sequencialCobrancaFim; //Valor padrão 99999999999999

   /*@Column(name = "ARCC_NR_TENTATIVAS_REENVIO")
   private Integer tentativasReenvio;*/ //Todos os valores null

   @Column(name = "ARCC_CD_CONVENIO", nullable = false)
   private String codigoConvenio;

   @Column(name = "ARCC_NR_SEQ_COBRANCA_GERADO")
   private Long sequenciaCobrancaGerado;

   @Column(name = "ARCC_IN_PADRAO", nullable = false)
   private Boolean indicadorPadrao; //Valor padrão false, mas pode ser true

   @Column(name = "ARCC_IN_USO")
   private Boolean habilitado = true;

   @Column(name = "ARCC_NR_VERSAO")
   private Integer versao = 0;

   @Column(name = "ARCC_TM_ULTIMA_ALTERACAO")
   private LocalDateTime ultimaAlteracao = LocalDateTime.now();

   public ArrecadadorContratoConvenio(Long id) {
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
      return MapperImpl.parseObject(this, ArrecadadorContratoConvenioDTO.class);
   }
}