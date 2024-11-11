package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.FaturaItem;
import com.stfn2.ggas.domain.FaturaItemTributacao;
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
public class FaturaItemTributacaoDTO extends BaseDTO {
   private Long id;
   private FaturaItem faturaItem;
   private Tributo tributo;
   private BigDecimal baseCalculoTributo = BigDecimal.ZERO;
   private BigDecimal baseCalculoTributoSubstitutivo = BigDecimal.ZERO;
   private BigDecimal percentualTributo = BigDecimal.ZERO;
   private BigDecimal valorTributo = BigDecimal.ZERO;
   private BigDecimal valorTributoSubstitutivo = BigDecimal.ZERO;

   public FaturaItemTributacaoDTO(FaturaItemTributacao entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}