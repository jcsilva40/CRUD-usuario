package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.config.security.domain.User;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Grupo;
import com.stfn2.ggas.domain.UsuarioGrupo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioGrupoDTO extends BaseDTO {
   private Long id;
   private User usuario;
   private Grupo grupo;

   public UsuarioGrupoDTO(UsuarioGrupo entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}