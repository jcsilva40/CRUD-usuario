package com.stfn2.ggas.services.componentes.calculojurosmulta;

import com.stfn2.ggas.domain.Contrato;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface CalculoJurosMulta {

        BigDecimal calcularJurosAtualizados(List<BigDecimal> indices, BigDecimal percentualJurosMora, BigDecimal saldo,
                                 Integer dias, Boolean mesAnterior);

        BigDecimal calcularMulta(BigDecimal percentualMulta, BigDecimal saldo, BigDecimal juros);

        BigDecimal calculoJurosImpontualidade(BigDecimal saldo, Contrato contrato, LocalDate dataRecebimentoAnterior,
                                              LocalDate dataFinalCalculo);

        BigDecimal calculoMultaImpontualidade(BigDecimal saldoAnteriorVencimento, Contrato contrato,
                                              LocalDate dataRecebimentoAnterior, LocalDate dataVencimento,
                                              LocalDate dataFinalCalculo);

        BigDecimal calcularAtualizacaoMonetaria(List<BigDecimal> indices, BigDecimal valorFatura,
                                                BigDecimal vrJuros, Integer dias, Boolean mesAnterior);
}
