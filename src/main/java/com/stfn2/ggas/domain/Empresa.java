package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.EmpresaDTO;
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
@Table(name = "EMPRESA")
public class Empresa extends BaseEntity {
  private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "EMPR_CD")
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_EMPR")
   @SequenceGenerator(name = "SQ_EMPR", sequenceName = "SQ_EMPR_CD", allocationSize = 1)
   private Long id;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "CLIE_CD", referencedColumnName = "CLIE_CD")
   private Cliente cliente;

   @Column(name = "EMPR_IN_PRINCIPAL")
   private Boolean principal;

   @Column(name = "EMPR_IN_USO")
   private Boolean habilitado = true;

   @Column(name = "EMPR_NR_VERSAO")
   private Integer versao = 0;

   @Column(name = "EMPR_TM_ULTIMAALTERACAO")
   private LocalDateTime ultimaAlteracao = LocalDateTime.now();

   public Empresa(Long id) {
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
      return MapperImpl.parseObject(this, EmpresaDTO.class);
   }
}
