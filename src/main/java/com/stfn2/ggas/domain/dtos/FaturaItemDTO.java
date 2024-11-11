package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FaturaItemDTO extends BaseDTO {

   private Long id;
   private NaturezaOperacaoCfop naturezaOperacaoCfop;
   private Fatura fatura;
   private CreditoDebitoARealizar creditoDebitoARealizar;
   private Rubrica rubrica;
   private Segmento segmento;
   private Tarifa tarifa;
   private BigDecimal consumo = BigDecimal.ZERO;
   private BigDecimal quantidadeItem = BigDecimal.ZERO;
   private BigDecimal valorFixoFaixaTarifa = BigDecimal.ZERO;
   private BigDecimal valorVariavelFaixaTarifa = BigDecimal.ZERO;
   private BigDecimal valorUnitario = BigDecimal.ZERO;
   private BigDecimal valorTotal = BigDecimal.ZERO;
   private Integer sequenciaItemNota = 0;
   private Integer totalPrestacoesPendentes = 0;
   private Integer volumeInicialFaixa = 0;
   private Integer volumeFinalFaixa = 0;

   public FaturaItemDTO(FaturaItem entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}