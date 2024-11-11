package com.stfn2.ggas.domain;

import com.stfn2.ggas.core.abstractClasses.BaseEntity;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.core.libs.MapperImpl;
import com.stfn2.ggas.domain.dtos.ProfissaoDTO;
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
@Table(name = "PROFISSAO")
@Entity
public class Profissao extends BaseEntity {

   @Serial
   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "PROF_CD")
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PROF")
   @SequenceGenerator(name = "SQ_PROF", sequenceName = "SQ_PROF_CD", allocationSize = 1)
   private Long id;

   @Column(name= "PROF_DS")
   private String descricao;

   @Column(name = "PROF_IN_USO")
   private Boolean habilitado = true;

   @Column(name = "PROF_NR_VERSAO")
   private Integer versao = 0;

   @Column(name = "PROF_TM_ULTIMA_ALTERACAO")
   private LocalDateTime ultimaAlteracao = LocalDateTime.now();

   @Override
   public BaseDTO convert() {
      return MapperImpl.parseObject(this, ProfissaoDTO.class);
   }
}
