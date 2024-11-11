package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;

//@NoArgsConstructor
public enum ImovelOrigemEnum {

   NOVO(1l, "Novo"),
   CONVERTIDO(2l, "Convertido");

   private Long id;
   private String descricao;

   private ImovelOrigemEnum(Long id, String descricao) {
      this.id = id;
      this.descricao=descricao;
   }

   private ImovelOrigemEnum() {

   }
   @JsonValue
   public Long getId() {
      return id;
   }

   public String getDescricao() {
      return descricao;
   }

   public static ImovelOrigemEnum toEnum(Long id) {
      if(id==null) {
         return null;
      }

      for (ImovelOrigemEnum x : ImovelOrigemEnum.values()) {
         if(id.equals(x.getId())) {
            return x;
         }
      }
      throw new IllegalArgumentException("Id inválido: " + id);
   }

}
