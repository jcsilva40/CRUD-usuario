package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Fatura;
import com.stfn2.ggas.domain.FaturaTributacao;
import com.stfn2.ggas.domain.Tributo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FaturaTributacaoDTO extends BaseDTO {
   private Long id;
   private Fatura fatura;
   private Tributo tributo;
   private BigDecimal baseCalculoTributo;
   private BigDecimal baseCalculoTributoSubstitutivo;
   private BigDecimal percentualTributo;
   private BigDecimal valorTributo;
   private BigDecimal valorTributoSubstitutivo;

   public FaturaTributacaoDTO(FaturaTributacao entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}