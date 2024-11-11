package com.stfn2.ggas.services.componentes.relatorio.relatorioFaturamento.dto;

import java.util.List;

public class RubricaDebitoCreditoDTO {

   private Long idRubrica;

   private List<ValoresRubricasDTO> listaValoresRubricas;

   public Long getIdRubrica() {
      return idRubrica;
   }

   public void setIdRubrica(Long idRubrica) {
      this.idRubrica = idRubrica;
   }

   public List<ValoresRubricasDTO> getListaValoresRubricas() {
      return listaValoresRubricas;
   }

   public void setListaValoresRubricas(List<ValoresRubricasDTO> listaValoresRubricas) {
      this.listaValoresRubricas = listaValoresRubricas;
   }

   @Override
   public String toString() {
      return "\nRubicaDebitoCreditoDTO{" +
              "\nidRubrica = " + getIdRubrica() +
              "\nlistaValoresRubricas = " + listaValoresRubricas +
              "\n}";
   }
}
