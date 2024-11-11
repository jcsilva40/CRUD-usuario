package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.CreditoDebitoARealizar;
import com.stfn2.ggas.domain.CreditoDebitoDetalhamento;
import com.stfn2.ggas.domain.LancamentoItemContabil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditoDebitoDetalhamentoDTO extends BaseDTO {
   private Long id;
   private CreditoDebitoARealizar creditoDebitoARealizar;
   private LancamentoItemContabil lancamentoItemContabil;
   private BigDecimal valor;

   public CreditoDebitoDetalhamentoDTO(CreditoDebitoDetalhamento entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}