package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EsferaPoderEnum {

   MUNICIPAL(1l, "Municipal"),
   ESTADUAL(2l, "Estadual"),
   FEDERAL(3l, "Federal"),
   PARTICULAR(4l, "Particular");

   private Long id;
   private String descricao;

   private EsferaPoderEnum(Long id, String descricao) {
      this.id = id;
      this.descricao=descricao;
   }

   @JsonValue
   public Long getId() {
      return id;
   }

   public String getDescricao() {
      return descricao;
   }

   public static EsferaPoderEnum toEnum(Long id) {
      if(id==null) {
         return null;
      }

      for (EsferaPoderEnum x : EsferaPoderEnum.values()) {
         if(id.equals(x.getId())) {
            return x;
         }
      }
      throw new IllegalArgumentException("Id inválido: " + id);
   }

}
