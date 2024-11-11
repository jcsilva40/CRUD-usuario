package com.stfn2.ggas.domain.dtos.basic;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.LiberacaoCronogramaFaturamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LiberacaoCronogramaFaturamentoBasicDTO extends BaseDTO {
   private Long id;
   private Long faturamentoGrupoId;
   private Long usuarioId;
   private Integer anoMes;
   private Integer ciclo;
   private String etapa;
   private Boolean status;

   public LiberacaoCronogramaFaturamentoBasicDTO(LiberacaoCronogramaFaturamento entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}
