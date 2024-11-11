package com.stfn2.ggas.domain.enumTypes;

import lombok.Getter;

@Getter
public enum MedicaoTipoEnum {

   DIARIA(1L, "DIARIA", "DD"),
   PERIODICA(2L, "PERIODICA", "PER");   

   private final Long id;
   private final String descricao;
   private final String abreviado;   
   
   MedicaoTipoEnum(Long id, String descricao, String abreviado) {
      this.id = id;
      this.descricao = descricao;
      this.abreviado = abreviado;      
   }

   public static MedicaoTipoEnum toEnum(Long id) {
      if(id==null) {
         return null;
      }

      for (MedicaoTipoEnum x : MedicaoTipoEnum.values()) {
         if(id.equals(x.getId())) {
            return x;
         }
      }
      throw new IllegalArgumentException("Id inválido: " + id);
   }
}
