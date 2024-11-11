package com.stfn2.ggas.services.componentes.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TarifaVigenciaAtualVO {
   private String tarifaDescricao;
   private LocalDate vigencia;
}
