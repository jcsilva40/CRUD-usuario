package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;

//@NoArgsConstructor
public enum ImovelTipoMedicaoGLPEnum {

   INDIVIDUAL_GLP(1l, "Individual GLP"),
   OUTRO(2l, "Outro");


   private Long id;
   private String descricao;

   private ImovelTipoMedicaoGLPEnum(Long id, String descricao) {
      this.id = id;
      this.descricao=descricao;
   }

   private ImovelTipoMedicaoGLPEnum() {

   }
   @JsonValue
   public Long getId() {
      return id;
   }

   public String getDescricao() {
      return descricao;
   }

   public static ImovelTipoMedicaoGLPEnum toEnum(Long id) {
      if(id==null) {
         return null;
      }

      for (ImovelTipoMedicaoGLPEnum x : ImovelTipoMedicaoGLPEnum.values()) {
         if(id.equals(x.getId())) {
            return x;
         }
      }
      throw new IllegalArgumentException("Id inválido: " + id);
   }

}
