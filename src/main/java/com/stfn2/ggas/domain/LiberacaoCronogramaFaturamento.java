package com.stfn2.ggas.domain;

import com.stfn2.ggas.config.security.domain.User;
import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CPGAS_LIBERACAO_CRONOGRAMA_FATURAMENTO")
public class LiberacaoCronogramaFaturamento extends BaseEntity {
   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "LICF_CD")
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_LICF")
   @SequenceGenerator(name = "SQ_LICF", sequenceName = "SQ_LICF_CD", allocationSize = 1)
   private Long id;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "FAGR_CD", referencedColumnName = "FAGR_CD")
   private FaturamentoGrupo faturamentoGrupo;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "USSI_CD", referencedColumnName = "USSI_CD")
   private User usuario;

   @Column(name = "LICF_ANOMES")
   private Integer anoMes;

   @Column(name = "LICF_CICLO")
   private Integer ciclo;

   @Column(name = "LICF_ETAPA")
   private String etapa;

   @Column(name = "LICF_ETAPA_STATUS")
   private Boolean status;

   @Column(name = "LICF_IN_USO")
   private Boolean habilitado = true;

   @Column(name = "LICF_ULTIMA_ALTERACAO")
   private LocalDateTime ultimaAlteracao = LocalDateTime.now();

   @Override
   public String getDescricao() {
      return "";
   }

   @Override
   public void setDescricao(String descricao) {
   }

   @Override
   protected Integer getVersao() {
      return 0;
   }

   @Override
   public BaseDTO convert() {
      return null;
   }
}
