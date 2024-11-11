package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.config.security.domain.User;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Permissao;
import com.stfn2.ggas.domain.UsuarioPermissoes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioPermissoesDTO extends BaseDTO {
   private Long id;
   private User usuario;
   private Permissao permissao;

   public UsuarioPermissoesDTO(UsuarioPermissoes entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}