package com.stfn2.ggas.services.componentes.relatorio.relatorioFaturamento.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
public class SubGasNaturalDTO {

   private String descricao;
   private BigDecimal quantidade; //Apesar do nome quantidade ela se refete ao consumo
   private BigDecimal valorUnitario;
   private BigDecimal valorTotal;
   private BigDecimal desconto;

   public String getDescricao() {
      return descricao;
   }

   public void setDescricao(String descricao) {
      this.descricao = descricao;
   }

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

   public BigDecimal getDesconto() {
      return desconto;
   }

   public void setDesconto(BigDecimal desconto) {
      this.desconto = desconto;
   }

   private BigDecimal arredondar(BigDecimal valor, RoundingMode roundingMode, int casasDecimais) {
      return Optional.ofNullable(valor)
              .map(v -> v.setScale(casasDecimais, roundingMode))
              .orElse(BigDecimal.ZERO);
   }

   @Override
   public String toString() {
      return "\nSubJurosDTO{" +
              "\ndescricao = '" + getDescricao() + '\'' +
              "\nquantidade = " + getQuantidade() +
              "\nvalorUnitario = " + getValorUnitario() +
              "\nvalor = " + getValorTotal() +
              "\n}";
   }

}
