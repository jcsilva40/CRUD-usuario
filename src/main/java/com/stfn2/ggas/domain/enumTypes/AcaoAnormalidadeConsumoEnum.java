package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum AcaoAnormalidadeConsumoEnum {

   MINIMO(1L, "MINIMO", true, true, false),
   MEDIA(2L, "MEDIA", true, true, false),
   NORMAL(3L, "NORMAL", false, true, false),
   MAIOR_MEDIA_POR_CONSUMO(4L, "MAIOR MEDIA/CONSUMO", false, true, false),
   MENOR_MEDIA_POR_CONSUMO(5L, "MENOR MEDIA/CONSUMO", false, true, false),
   NAO_OCORRE(6L, "NAO OCORRE", true, true, false);   

   private final Long id;
   private final String descricao;
   private final Boolean semLeitura; 
   private final Boolean comLeitura;
   private final Boolean volumeAFaturar;
   
   AcaoAnormalidadeConsumoEnum(Long id, String descricao, Boolean semLeitura, Boolean comLeitura, Boolean volumeAFaturar) {
      this.id = id;
      this.descricao = descricao;
      this.semLeitura = semLeitura;
      this.comLeitura = comLeitura;
      this.volumeAFaturar = volumeAFaturar;
   }

   @JsonCreator
   public static AcaoAnormalidadeConsumoEnum toEnum(@JsonProperty("acaoAnormalidadeConsumo") Long id) {
      if(id==null) {
         return null;
      }

      for (AcaoAnormalidadeConsumoEnum x : AcaoAnormalidadeConsumoEnum.values()) {
         if(id.equals(x.getId())) {
            return x;
         }
      }
      throw new IllegalArgumentException("Id inválido: " + id);
   }
   
   @JsonValue
   public Long getId() {
      return id;
   }
}
