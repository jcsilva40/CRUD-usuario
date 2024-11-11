package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.FaturaMotivoRevisao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FaturaMotivoRevisaoDTO extends BaseDTO {
   private Long id;
   private String descricao;
   private Boolean returadaAutomatica = false;
   private Integer diasPrazoMaximo = 0;

   public FaturaMotivoRevisaoDTO(FaturaMotivoRevisao entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}