package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum StatusCampanhaEnum {

   ID_ENT_CONT_PEND_APROVACAO(931L, "ID_ENT_CONT_PEND_APROVACAO", ""),
   ID_ENT_CONT_APROVADO(932L, "ID_ENT_CONT_APROVADO", ""),
   ID_ENT_CONT_PEND_ENCERRAMENTO(933L, "ID_ENT_CONT_PEND_ENCERRAMENTO", ""),
   ID_ENT_CONT_ENCERRADO(934L, "ID_ENT_CONT_ENCERRADO", "");
   
   private final Long id;
   private final String descricao; 
   private final String abreviado;

   StatusCampanhaEnum(Long id, String descricao, String abreviado) {
      this.id = id;
      this.descricao = descricao;
      this.abreviado = abreviado;      
   }

   @JsonValue
   public Long getId() {
      return id;
   }

   public static StatusCampanhaEnum toEnum(Long id) {
      if(id==null) {
         return null;
      }

      for (StatusCampanhaEnum x : StatusCampanhaEnum.values()) {
         if(id.equals(x.getId())) {
            return x;
         }
      }
      throw new IllegalArgumentException("Id inválido: " + id);
   }
}