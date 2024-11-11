package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.UnidadeDTO;
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
@Table(name = "UNIDADE")
public class Unidade extends BaseEntity {
   @Serial
   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "UNID_CD")
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_UNID")
   @SequenceGenerator(name = "SQ_UNID", sequenceName = "SQ_UNID_CD", allocationSize = 1)
   private Long id;

   @ManyToOne
   @JoinColumn(name = "UNCL_CD", referencedColumnName = "UNCL_CD")
   private UnidadeClasse unidadeClasse;

   @Column(name = "UNID_DS")
   private String descricao;

   @Column(name = "UNID_DS_ABREVIADO")
   private String abreviado;

   @Column(name = "UNID_IN_PADRAO")
   private Boolean padrao;

   @Column(name = "UNID_IN_USO")
   private Boolean habilitado = true;

   @Column(name = "UNID_NR_VERSAO")
   private Integer versao = 0;

   @Column(name = "UNID_TM_ULTIMA_ALTERACAO")
   private LocalDateTime ultimaAlteracao = LocalDateTime.now();

   public Unidade(Long id) {
      super(id);
      this.id = id;
   }

   @Override
   public BaseDTO convert() {
      return MapperImpl.parseObject(this, UnidadeDTO.class);
   }
}