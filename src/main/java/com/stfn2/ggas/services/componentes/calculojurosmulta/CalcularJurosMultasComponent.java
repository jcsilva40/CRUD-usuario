package com.stfn2.ggas.services.componentes.calculojurosmulta;

import com.stfn2.ggas.domain.Contrato;
import com.stfn2.ggas.services.ContratoService;
import com.stfn2.ggas.tools.ToolDate;
import com.stfn2.ggas.tools.ToolNumber;
import com.stfn2.ggas.tools.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Component
public class CalcularJurosMultasComponent implements CalculoJurosMulta{

   @Autowired
   private ContratoService contratoService;

   @Override
   public BigDecimal calcularJurosAtualizados(List<BigDecimal> indices, BigDecimal percentualJurosMora, BigDecimal saldo,
                                   Integer dias, Boolean mesAnterior) {
      BigDecimal juros = calcularJuros(percentualJurosMora, saldo, dias);
      BigDecimal Atualizacao = calcularAtualizacaoMonetaria(indices, saldo, juros, dias, mesAnterior);

      return juros.add(Atualizacao);
   }

   public static BigDecimal calcularJuros(BigDecimal vrIndiceJuros, BigDecimal valorFatura, Integer dias) {
      BigDecimal percentualJuros = (vrIndiceJuros.divide(BigDecimal.valueOf(30), 10, RoundingMode.HALF_UP)
              .multiply(BigDecimal.valueOf(dias)));
      return valorFatura.multiply(percentualJuros).setScale(2, RoundingMode.HALF_UP);
   }

   @Override
   public BigDecimal calcularMulta(BigDecimal percentualMulta, BigDecimal saldo, BigDecimal juros) {
      if (juros == null) {
         juros = BigDecimal.ZERO;
      }
      return (saldo.add(juros)).multiply(percentualMulta).setScale(2, RoundingMode.HALF_UP);
   }

   public static BigDecimal calcularMulta(BigDecimal vrIndiceMulta, BigDecimal valorFatura,
                                          BigDecimal vrAtualizacaoMonetaria, BigDecimal vrJuros) {
      return (valorFatura.add(vrJuros).add(vrAtualizacaoMonetaria)).multiply(vrIndiceMulta).setScale(2,
              RoundingMode.HALF_UP);
   }

   @Override
   public BigDecimal calculoJurosImpontualidade(BigDecimal saldo, Contrato contrato, LocalDate dataInicioCalculo, LocalDate dataFinalCalculo) {

         BigDecimal acrescimoJuros = BigDecimal.ZERO;

         if (ToolNumber.isPositive(saldo) && ToolUtil.coalescenciaNula(contrato.getIndicadorJuros(), false)) {

            BigDecimal percentualJurosMora = contrato.getPercentualJurosMora();

            if (percentualJurosMora != null) {

               BigDecimal expoente = ToolDate.gerarDiasPorMesDeItervaloDatas(dataInicioCalculo, dataFinalCalculo);

               percentualJurosMora = BigDecimal
                       .valueOf(
                               Math.pow(percentualJurosMora.add(BigDecimal.ONE).doubleValue(), expoente.doubleValue()))
                       .subtract(BigDecimal.ONE);

               acrescimoJuros = saldo.multiply(percentualJurosMora);
            }

         }
         return acrescimoJuros;
      }

   @Override
   public BigDecimal calculoMultaImpontualidade(BigDecimal saldoAnteriorVencimento, Contrato contrato, LocalDate dataRecebimentoAnterior, LocalDate dataVencimento, LocalDate dataPagamento) {
      BigDecimal acrescimoMulta = BigDecimal.ZERO;

      if (ToolNumber.isPositive(saldoAnteriorVencimento) && ToolUtil.coalescenciaNula(contrato.getIndicadorMulta(), false)
              && ToolDate.menorOuIgualQue(dataRecebimentoAnterior, dataVencimento)) {

         BigDecimal fatorCorrecaoIndiceFinanceiro = BigDecimal.ONE;
         if (contrato.getIndiceFinanceiro() != null) {
            fatorCorrecaoIndiceFinanceiro = contratoService
                    .fatorCorrecaoIndiceFinanceiro(contrato.getIndiceFinanceiro(), dataVencimento, dataPagamento);
         }

         BigDecimal valorCorrigido = saldoAnteriorVencimento.multiply(fatorCorrecaoIndiceFinanceiro);

         BigDecimal percentualMulta = contrato.getPercentualMulta();

         if (percentualMulta != null) {
            acrescimoMulta = valorCorrigido.multiply(percentualMulta);
         }
      }
      return acrescimoMulta;
   }

   @Override
   public BigDecimal calcularAtualizacaoMonetaria(List<BigDecimal> indices, BigDecimal valorFatura, BigDecimal vrJuros, Integer dias, Boolean mesAnterior) {
      BigDecimal percentualAtualizacaoFinanceira = BigDecimal.ONE;
      BigDecimal atualizacaoMonetaria = BigDecimal.ZERO;

      if (indices.size() > 0) {
         if (mesAnterior) {
            percentualAtualizacaoFinanceira = indices.get(0);
            atualizacaoMonetaria = valorFatura.add(vrJuros).multiply(percentualAtualizacaoFinanceira).setScale(2,
                    RoundingMode.HALF_UP);
         } else {
            for (BigDecimal valor : indices) {
               percentualAtualizacaoFinanceira = valor;
               atualizacaoMonetaria = atualizacaoMonetaria.add(valorFatura.add(vrJuros)
                       .multiply(percentualAtualizacaoFinanceira).setScale(2, RoundingMode.HALF_UP));
            }
         }
      }
      return atualizacaoMonetaria;
   }
}
