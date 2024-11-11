package com.stfn2.ggas.domain.dtos.basic;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Permissao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PermissaoBasicDTO extends BaseDTO {
   private Long id;
   private String descricao;
   private String complemento;

   public PermissaoBasicDTO(Permissao entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}