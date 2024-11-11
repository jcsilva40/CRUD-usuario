package com.stfn2.ggas.services.componentes.calculotarifa.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DadosFaixaVO implements Serializable {
   private static final long serialVersionUID = 1L;

   private BigDecimal valor;

}
