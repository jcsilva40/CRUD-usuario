package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.FaturamentoMensagem;
import com.stfn2.ggas.domain.FaturamentoMensagemVenciment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FaturamentoMensagemVencimentDTO extends BaseDTO {
   private Long id;
   private FaturamentoMensagem faturamentoMensagem;
   private Integer diaVencimento = 0;

   public FaturamentoMensagemVencimentDTO(FaturamentoMensagemVenciment entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}