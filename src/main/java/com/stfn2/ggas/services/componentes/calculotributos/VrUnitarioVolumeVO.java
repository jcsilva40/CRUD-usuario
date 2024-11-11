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
public class VrUnitarioVolumeVO {
   private BigDecimal vrUnitario = BigDecimal.ZERO;
   private BigDecimal volume = BigDecimal.ZERO;
}
