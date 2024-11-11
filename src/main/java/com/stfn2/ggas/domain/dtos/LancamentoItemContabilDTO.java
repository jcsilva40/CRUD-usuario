package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.EntidadeConteudo;
import com.stfn2.ggas.domain.LancamentoItemContabil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LancamentoItemContabilDTO extends BaseDTO {
   private Long id;
   private EntidadeConteudo tipoDebitoCredito;
   private String descricao;
   private String abreviado;

   public LancamentoItemContabilDTO(LancamentoItemContabil entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}