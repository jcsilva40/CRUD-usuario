package com.stfn2.ggas.domain.dtos.basic;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.UsuarioGrupo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioGrupoBasicDTO extends BaseDTO {
   private Long id;
   private Long usuarioId;
   private String usuarioNome;
   private Long grupoId;
   private String grupoDescricao;

   public UsuarioGrupoBasicDTO(UsuarioGrupo entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}