package com.stfn2.ggas.domain;

import com.stfn2.ggas.config.security.domain.User;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.CampanhaDescontoVincularDTO;
import com.stfn2.ggas.domain.enumTypes.StatusCampanhaEnum;
import com.stfn2.ggas.domain.enumTypes.converter.StatusCampanhaConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CPGAS_CAMPANHA_DESCONTO_VINCULAR")
public class CampanhaDescontoVincular extends BaseEntity {
   @Serial
   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "CADV_CD")
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CADV")
   @SequenceGenerator(name = "SQ_CADV_CD", sequenceName = "SQ_CADV_CD", allocationSize = 1)
   private Long id;

   @Column(name = "HABILITADO")
   private Boolean habilitado = true;

   @Column(name = "VERSAO")
   private Integer versao = 0;

   @Column(name = "ULTIMA_ALTERACAO")
   private LocalDateTime ultimaAlteracao = LocalDateTime.now();

   @Column(name = "DATA_SOLICITACAO")
   private LocalDate dataSolicitacao;

   @Column(name = "DATA_APROVACAO")
   private LocalDateTime dataAprovacao;

   @Column(name = "PERIODO_FATURADO")
   private Short periodoFaturado;

   @Column(name = "ANO_MES")
   private Integer anoMes;

   @Column(name = "CICLO")
   private Integer ciclo;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "CADE_CD", nullable = true)
   private CampanhaDesconto campanhaDesconto;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "CONT_CD")
   private Contrato contrato;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "IMOV_CD")
   private Imovel imovel;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "USSI_CD_SOLICITANTE", nullable = false)
   private User solicitante;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "USSI_CD_APROVADOR", nullable = false)
   private User aprovador;

   /*@ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "STATUS_CAMPANHA", nullable = false)*/
   @Convert(converter = StatusCampanhaConverter.class)
   @Column(name = "STATUS_CAMPANHA")
   private StatusCampanhaEnum statusCampanha;

   public CampanhaDescontoVincular(Long id) {
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
      return MapperImpl.parseObject(this, CampanhaDescontoVincularDTO.class);
   }
}