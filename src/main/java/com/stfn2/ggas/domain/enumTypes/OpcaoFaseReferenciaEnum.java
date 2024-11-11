package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
/**
 * Referente a entidade classe número 9
 */
public enum OpcaoFaseReferenciaEnum {

   DIA_FIXO(28L, "DIA FIXO", ""),
   DIAS_CORRIDOS(29L, "DIAS CORRIDOS", ""),
   DIAS_UTEIS(30L, "DIAS ÚTEIS", ""),
   DIA_FIXO_ESTENDIDO(500L, "DIA FIXO ESTENDIDO", "");
   
   private final Long id;
   private final String descricao; 
   private final String abreviado;

   OpcaoFaseReferenciaEnum(Long id, String descricao, String abreviado) {
      this.id = id;
      this.descricao = descricao;
      this.abreviado = abreviado;      
   }

   @JsonValue
   public Long getId() {
      return id;
   }

   public static OpcaoFaseReferenciaEnum toEnum(Long id) {
      if(id==null) {
         return null;
      }

      for (OpcaoFaseReferenciaEnum x : OpcaoFaseReferenciaEnum.values()) {
         if(id.equals(x.getId())) {
            return x;
         }
      }
      throw new IllegalArgumentException("Id inválido: " + id);
   }
}