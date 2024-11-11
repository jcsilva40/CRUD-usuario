package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;

//@NoArgsConstructor
public enum ImovelTipoMedicaoEnum {

   INDIVIDUAL(1l, "Individual"),
   COLETIVA_EMRP(2l, "Coletiva EMRP"),
   COLETIVA_ERP(3l, "Coletiva ERP");


   private Long id;
   private String descricao;

   private ImovelTipoMedicaoEnum(Long id, String descricao) {
      this.id = id;
      this.descricao=descricao;
   }

   private ImovelTipoMedicaoEnum() {

   }
   @JsonValue
   public Long getId() {
      return id;
   }

   public String getDescricao() {
      return descricao;
   }

   public static ImovelTipoMedicaoEnum toEnum(Long id) {
      if(id==null) {
         return null;
      }

      for (ImovelTipoMedicaoEnum x : ImovelTipoMedicaoEnum.values()) {
         if(id.equals(x.getId())) {
            return x;
         }
      }
      throw new IllegalArgumentException("Id inválido: " + id);
   }

}
