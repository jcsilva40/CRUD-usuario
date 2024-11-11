package com.stfn2.ggas.services.componentes.relatorio.relatorioFaturamento.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
public class SubJurosDTO {

   private BigDecimal quantidade;
   private BigDecimal valorUnitario;
   private BigDecimal valorTotal;

   public BigDecimal getQuantidade() {
      return arredondar(quantidade, RoundingMode.HALF_EVEN, 4);
   }

   public void setQuantidade(BigDecimal quantidade) {
      this.quantidade = quantidade;
   }

   public BigDecimal getValorUnitario() {
      return arredondar(valorUnitario, RoundingMode.HALF_EVEN, 4);
   }

   public void setValorUnitario(BigDecimal valorUnitario) {
      this.valorUnitario = valorUnitario;
   }

   public BigDecimal getValorTotal() {
      return arredondar(valorTotal, RoundingMode.HALF_EVEN, 2);
   }

   public void setValorTotal(BigDecimal valorTotal) {
      this.valorTotal = valorTotal;
   }

   private BigDecimal arredondar(BigDecimal valor, RoundingMode roundingMode, int casasDecimais) {
      return Optional.ofNullable(valor)
              .map(v -> v.setScale(casasDecimais, roundingMode))
              .orElse(BigDecimal.ZERO);
   }

   @Override
   public String toString() {
      return "\nSubJurosDTO{" +
              "\nquantidade = " + getQuantidade() +
              "\nvalorUnitario = " + getValorUnitario() +
              "\nvalorTotal = " + getValorTotal() +
              "\n}";
   }
}