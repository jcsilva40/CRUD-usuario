package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.FaturaItem;
import com.stfn2.ggas.domain.FaturaItemImpressao;
import com.stfn2.ggas.domain.Tarifa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FaturaItemImpressaoDTO extends BaseDTO {

   private Long id;
   private Tarifa tarifa;
   private FaturaItem faturaItem;
   private String descricaoItem;
   private String descricaoDesconto;
   private LocalDateTime dataInicial;
   private LocalDateTime dataFinal;
   private Integer diasConsumo = 0;
   private BigDecimal valorDesconto = BigDecimal.ZERO;
   private BigDecimal valorTotal = BigDecimal.ZERO;
   private BigDecimal valorUnitario = BigDecimal.ZERO;
   private BigDecimal consumo = BigDecimal.ZERO;
   private Boolean desconto = false;

   public FaturaItemImpressaoDTO(FaturaItemImpressao entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}