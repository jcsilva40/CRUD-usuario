package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum ConsumoTipoEnum {

   REAL(1L, "REAL", "RE", true, false),
   MEDIA(2L, "MEDIA", "MD", true, false),
   INFORMADO(3L, "INFORMADO", "IN", true, false),
   MINIMO(4L, "MÍNIMO", "MI", true, false),
   SEM_CONSUMO(5L, "SEM CONSUMO", "SC", true, false),
   ESTIMADO(6L, "ESTIMADO", "ES", true, false),
   NAO_APURADO(7L, "NÃO APURADO", "NA", true, false),
   AJUSTADO(8L, "AJUSTADO", "AJ", true, false),
   REAL_MEDIDOR_ATUAL(9L, "REAL MEDIDOR ATUAL", "REA", true, false),
   REAL_COMPOSTO(10L, "REAL COMPOSTO", "REAC", true, false);
   
   private final Long id;
   private final String descricao; 
   private final String abreviado;
   private final Boolean correcaoPTZ;
   private final Boolean correcaoPCS;

   ConsumoTipoEnum(Long id, String descricao, String abreviado, Boolean correcaoPTZ, Boolean correcaoPCS) {
      this.id = id;
      this.descricao = descricao;
      this.abreviado = abreviado;
      this.correcaoPTZ = correcaoPTZ;
      this.correcaoPCS = correcaoPCS;
   }

   @JsonValue
   public Long getId() {
      return id;
   }

   public static ConsumoTipoEnum toEnum(Long id) {
      if(id==null) {
         return null;
      }

      for (ConsumoTipoEnum x : ConsumoTipoEnum.values()) {
         if(id.equals(x.getId())) {
            return x;
         }
      }
      throw new IllegalArgumentException("Id inválido: " + id);
   }
}