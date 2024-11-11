package com.stfn2.ggas.domain.enumTypes;

import lombok.Getter;

@Getter
public enum TributosEnum {

   PIS(1L, "PIS"),
   COFINS(2L, "COFINS"),
   ICMS(3L, "ICMS"),
   ISS(4L, "ISS"),
   IRRF(5L, "IRRF");

   private final Long id;
   private final String descricao;

   TributosEnum(Long id, String descricao) {
      this.id = id;
      this.descricao=descricao;
   }

   public static TributosEnum toEnum(Long id) {
      if(id==null) {
         return null;
      }

      for (TributosEnum x : TributosEnum.values()) {
         if(id.equals(x.getId())) {
            return x;
         }
      }
      throw new IllegalArgumentException("Id inválido: " + id);
   }
}
