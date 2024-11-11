package com.stfn2.ggas.domain.dtos.basic;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.UsuarioPermissoes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioPermissoesBasicDTO extends BaseDTO {
   private Long id;
   private Long permissaoId;
   private String permissaoDescricao;
   private Long userId;
   private String userNome;

   public UsuarioPermissoesBasicDTO(UsuarioPermissoes entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}