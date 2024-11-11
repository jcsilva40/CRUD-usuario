package com.stfn2.ggas.services.componentes.calculotarifa.vo;

import com.stfn2.ggas.domain.TarifaVigencia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DadosVigenciaVO implements Serializable {

   private static final long serialVersionUID = 1L;

   private TarifaVigencia tarifaVigencia;

   private BigDecimal consumoApurado;
   private BigDecimal consumoFaturado;
   private BigDecimal baseCalculoImpostos;
   private BigDecimal valorTotal;
   private BigDecimal valorTotalSemSubstituicao;
   private BigDecimal valorTotalSemTributo;
   private BigDecimal valorDesconto;
   private BigDecimal valorTotalComDescontoComImposto;

   private LocalDate dataPeriodoInicial;
   private LocalDate dataPeriodoFinal;

   private List<DadosTributosVO> colecaoTributos;
   private List<DadosFaixaVO> dadosFaixa;

}
