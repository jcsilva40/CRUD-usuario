package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Profissao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfissaoDTO extends BaseDTO {

   private Long id;
   private String descricao;
   private Boolean habilitado;

   public ProfissaoDTO (Profissao entity) {super(); 	}

   @Override
   public Long getId() {
      return this.id;
   }
}
