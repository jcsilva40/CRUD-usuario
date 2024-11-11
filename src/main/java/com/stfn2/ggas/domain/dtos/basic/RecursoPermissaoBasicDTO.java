package com.stfn2.ggas.domain.dtos.basic;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.RecursoPermissao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecursoPermissaoBasicDTO extends BaseDTO {
   private Long id;
   private Long recursoId;
   private String recursoUrl;
   private String recursoVerboHttp;
   private Long permissaoId;
   private String permissaoDescricao;

   public RecursoPermissaoBasicDTO(RecursoPermissao entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}