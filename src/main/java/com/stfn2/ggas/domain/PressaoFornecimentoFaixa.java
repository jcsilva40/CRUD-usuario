package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.PressaoFornecimentoFaixaDTO;
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
@Table(name = "PRESSAO_FORNECIMENTO_FAIXA")
public class PressaoFornecimentoFaixa extends BaseEntity {
   @Serial
   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "PRFF_CD")
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PRFF")
   @SequenceGenerator(name = "SQ_PRFF", sequenceName = "SQ_PRFF_CD", allocationSize = 1)
   private Long id;

   @Column(name = "PRFF_IN_USO")
   private Boolean habilitado = true;

   @Column(name = "PRFF_NR_VERSAO")
   private Integer versao = 0;

   @Column(name = "PRFF_TM_ULTIMA_ALTERACAO")
   private LocalDateTime ultimaAlteracao = LocalDateTime.now();

   @Column(name = "PRFF_MD_MINIMO", nullable = false)
   private BigDecimal medidaMinimo;

   @Column(name = "PRFF_MD_MAXIMO", nullable = false)
   private BigDecimal medidaMaximo;

   @Column(name = "PRFF_NR_FATOR_Z")
   private BigDecimal numeroFatorZ;

   @Column(name = "PRFF_NR_FATOR_CORRECAO_PTZ_PCS")
   private BigDecimal numeroFatorCorrecaoPTZPCS;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "UNID_CD_PRESSAO", nullable = false)
   private Unidade unidadePressao; //Valor Padrão 42

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "SEGM_CD")
   private Segmento segmento;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "ENCO_CD_CLASSE_PRESSAO")
   private EntidadeConteudo classePressao; //Valor padrão 407

   // atributo não persitido para indicar vinculo com a entida contratoPontoConsumo
   /*private Boolean indicadorFaixaVinculada;*/

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "ENCO_CD_IN_CORRECAO_PT")
   private EntidadeConteudo indicadorCorrecaoPT; //Valor padrão 543

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "ENCO_CD_IN_CORRECAO_Z")
   private EntidadeConteudo indicadorCorrecaoZ; //Valor padrão 546
   

   public PressaoFornecimentoFaixa(Long id) {
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
      return MapperImpl.parseObject(this, PressaoFornecimentoFaixaDTO.class);
   }
}