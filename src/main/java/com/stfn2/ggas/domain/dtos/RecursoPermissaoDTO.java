package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Permissao;
import com.stfn2.ggas.domain.Recurso;
import com.stfn2.ggas.domain.RecursoPermissao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecursoPermissaoDTO extends BaseDTO {
   private Long id;
   private Recurso recurso;
   private Permissao permissao;
   private Boolean habilitado;

   public RecursoPermissaoDTO(RecursoPermissao entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}