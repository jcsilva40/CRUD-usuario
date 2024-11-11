package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum ModalidadeMedicaoEnum {

   APLICADO_PARA_AMBOS(930L, "APLICADO PARA AMBOS", ""),
   COLETIVO(929L, "COLETIVO", ""),
   INDIVIDUAL(928L, "INDIVIDUAL", "");
   
   private final Long id;
   private final String descricao; 
   private final String abreviado;

   ModalidadeMedicaoEnum(Long id, String descricao, String abreviado) {
      this.id = id;
      this.descricao = descricao;
      this.abreviado = abreviado;      
   }

   @JsonValue
   public Long getId() {
      return id;
   }

   public static ModalidadeMedicaoEnum toEnum(Long id) {
      if(id==null) {
         return null;
      }

      for (ModalidadeMedicaoEnum x : ModalidadeMedicaoEnum.values()) {
         if(id.equals(x.getId())) {
            return x;
         }
      }
      throw new IllegalArgumentException("Id inválido: " + id);
   }
}