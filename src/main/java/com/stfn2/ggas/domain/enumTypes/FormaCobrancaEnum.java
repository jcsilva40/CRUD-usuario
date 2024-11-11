package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum FormaCobrancaEnum {

    NENHUM(886L, "Nenhum", ""),
    ARRECADACAO(575L, "Arrecadação", ""),
    BOLETO_BANCARIO(576L, "Boleto Bancário", ""),
    DEBITO_AUTOMATICO(577L, "Débito Automático", ""); 

    private final Long id;
    private final String descricao;
    private final String abreviado;  
   
    FormaCobrancaEnum(Long id, String descricao, String abreviado) {
       this.id = id;
       this.descricao = descricao;
       this.abreviado = abreviado;
    }
   
    @JsonValue
    public Long getId() {
            return id;
    }

   public static FormaCobrancaEnum toEnum(Long id) {
      if(id==null) {
         return null;
      }

      for (FormaCobrancaEnum x : FormaCobrancaEnum.values()) {
         if(id.equals(x.getId())) {
            return x;
         }
      }
      throw new IllegalArgumentException("Id inválido: " + id);
   }
}
