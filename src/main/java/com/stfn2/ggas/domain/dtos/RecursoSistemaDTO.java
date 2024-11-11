package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.OperacaoSistema;
import com.stfn2.ggas.domain.RecursoSistema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecursoSistemaDTO extends BaseDTO {
   private Long id;
   private OperacaoSistema operacaoSistema;
   private String descricao;
   private Boolean web;

   public RecursoSistemaDTO(RecursoSistema entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}