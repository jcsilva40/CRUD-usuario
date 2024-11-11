package com.stfn2.ggas.domain.dtos.basic;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.GrupoPermissao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GrupoPermissaoBasicDTO extends BaseDTO {
   private Long id;
   private Long permissaoId;
   private String permissaoDescricao;
   private Long grupoId;
   private String grupoDescricao;

   public GrupoPermissaoBasicDTO(GrupoPermissao entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}