package com.stfn2.ggas.services.componentes.calculotributos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AliquotasVO {
    private BigDecimal icms = BigDecimal.ZERO;
    private BigDecimal pis = BigDecimal.ZERO;
    private BigDecimal cofins = BigDecimal.ZERO;
    private BigDecimal iss = BigDecimal.ZERO;
    private BigDecimal irrf = BigDecimal.ZERO;
}
