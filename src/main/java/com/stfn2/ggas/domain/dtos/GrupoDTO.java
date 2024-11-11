package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Grupo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GrupoDTO extends BaseDTO {
   private Long id;
   private String descricao;
   private String complemento;

   public GrupoDTO(Grupo entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}