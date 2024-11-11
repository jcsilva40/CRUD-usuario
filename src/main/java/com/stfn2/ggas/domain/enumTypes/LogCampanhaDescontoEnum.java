package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum LogCampanhaDescontoEnum {

   INSERIR(1L, "INSERÇÃO", ""),
   ALTERAR(2L, "ALTERAÇÃO", ""),
   EXCLUIR(3L, "EXCLUSÃO", ""),
   SOLICITAR_APROVAR_VINCULO(4L, "SOLICITAÇÃO DE APROVAÇÃO DO VÍNCULO", ""),
   APROVAR_VINCULO(5L, "APROVAÇÃO DO VÍNCULO", ""),
   REPROVAR_VINCULO(6L, "REPROVAÇÃO DO VÍNCULO", ""),
   SOLICITAR_ENCERRAMENTO(7L, "SOLICITAÇÃO DE ENCERRAMENTO", ""),   
   ARPOVAR_ENCERRAMENTO(8L, "APROVAÇÃO ENCERRAMENTO", ""),
   REPROVAR_ENCERRAMENTO(9L, "REPROVAÇÃO ENCERRAMENTO", "");
   
   private final Long id;
   private final String descricao; 
   private final String abreviado;

   LogCampanhaDescontoEnum(Long id, String descricao, String abreviado) {
      this.id = id;
      this.descricao = descricao;
      this.abreviado = abreviado;      
   }

   @JsonValue
   public Long getId() {
      return id;
   }

   public static LogCampanhaDescontoEnum toEnum(Long id) {
      if(id==null) {
         return null;
      }

      for (LogCampanhaDescontoEnum x : LogCampanhaDescontoEnum.values()) {
         if(id.equals(x.getId())) {
            return x;
         }
      }
      throw new IllegalArgumentException("Id inválido: " + id);
   }
}