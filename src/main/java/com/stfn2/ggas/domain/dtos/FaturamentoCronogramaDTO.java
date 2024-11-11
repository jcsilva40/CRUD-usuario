package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.FaturamentoCronograma;
import com.stfn2.ggas.domain.FaturamentoGrupo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FaturamentoCronogramaDTO extends BaseDTO {
   private Long id;
   private FaturamentoGrupo faturamentoGrupo;
   private Integer anoMesFaturamento = 0;
   private Integer ciclo = 0;

   public FaturamentoCronogramaDTO(FaturamentoCronograma entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}