package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Grupo;
import com.stfn2.ggas.domain.GrupoPermissao;
import com.stfn2.ggas.domain.Permissao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GrupoPermissaoDTO extends BaseDTO {
  private Long id;
	private Grupo grupo;
  private Permissao permissao;
  private Boolean habilitado;

   public GrupoPermissaoDTO(GrupoPermissao entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}