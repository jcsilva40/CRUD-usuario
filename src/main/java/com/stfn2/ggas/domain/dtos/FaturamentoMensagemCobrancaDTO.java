package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.EntidadeConteudo;
import com.stfn2.ggas.domain.FaturamentoMensagem;
import com.stfn2.ggas.domain.FaturamentoMensagemCobranca;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FaturamentoMensagemCobrancaDTO extends BaseDTO {
   private Long id;
   private EntidadeConteudo formaCobranca;
   private FaturamentoMensagem faturamentoMensagem;

   public FaturamentoMensagemCobrancaDTO(FaturamentoMensagemCobranca entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}