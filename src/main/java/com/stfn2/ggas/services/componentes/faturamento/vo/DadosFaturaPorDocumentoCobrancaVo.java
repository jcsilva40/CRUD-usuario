package com.stfn2.ggas.services.componentes.faturamento.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DadosFaturaPorDocumentoCobrancaVo {
   private BigDecimal valorTotal;
   private Integer anoMes;
   private Integer ciclo;
}