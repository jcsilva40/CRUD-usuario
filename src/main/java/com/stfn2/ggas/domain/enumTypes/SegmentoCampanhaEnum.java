package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum SegmentoCampanhaEnum {

   COMERCIAL(926L, "COMERCIAL", ""),
   INDUSTRIAL(927L, "INDUSTRIAL", ""),
   RESIDENCIAL(925L, "RESIDENCIAL", "");
   
   private final Long id;
   private final String descricao; 
   private final String abreviado;

   SegmentoCampanhaEnum(Long id, String descricao, String abreviado) {
      this.id = id;
      this.descricao = descricao;
      this.abreviado = abreviado;      
   }

   @JsonValue
   public Long getId() {
      return id;
   }

   public static SegmentoCampanhaEnum toEnum(Long id) {
      if(id==null) {
         return null;
      }

      for (SegmentoCampanhaEnum x : SegmentoCampanhaEnum.values()) {
         if(id.equals(x.getId())) {
            return x;
         }
      }
      throw new IllegalArgumentException("Id inválido: " + id);
   }
}