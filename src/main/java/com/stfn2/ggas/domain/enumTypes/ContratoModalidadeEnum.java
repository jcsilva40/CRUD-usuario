package com.stfn2.ggas.domain.enumTypes;

import lombok.Getter;

@Getter
public enum ContratoModalidadeEnum {

   FIRME_INFLEXIVEL(1L, "FIRME INFLEXIVEL"),
   FIRME_FLEXIVEL(2L, "FIRME FLEXIVEL"),
   INTERRUPTIVEL(3L, "INTERRUPTIVEL");

   private final Long id;
   private final String descricao;

   ContratoModalidadeEnum(Long id, String descricao) {
      this.id = id;
      this.descricao=descricao;
   }

   public static ContratoModalidadeEnum toEnum(Long id) {
      if(id==null) {
         return null;
      }

      for (ContratoModalidadeEnum x : ContratoModalidadeEnum.values()) {
         if(id.equals(x.getId())) {
            return x;
         }
      }
      throw new IllegalArgumentException("Id inválido: " + id);
   }
}
