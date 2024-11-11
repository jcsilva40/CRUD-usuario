package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ClienteImovelRelacionamentoEnum {

   ADMINISTRADOR(1l, "Administrador"),
   INQUILINO(2l, "Inquilino"),
   PROPRIETARIO(3l, "Proprietário");


   private Long id;
   private String descricao;

   private ClienteImovelRelacionamentoEnum(Long id, String descricao) {
      this.id = id;
      this.descricao=descricao;
   }
   private ClienteImovelRelacionamentoEnum() {

   }

   @JsonValue
   public Long getId() {
      return id;
   }

   public String getDescricao() {
      return descricao;
   }

   public static ClienteImovelRelacionamentoEnum toEnum(Long id) {
      if(id==null) {
         return null;
      }

      for (ClienteImovelRelacionamentoEnum x : ClienteImovelRelacionamentoEnum.values()) {
         if(id.equals(x.getId())) {
            return x;
         }
      }
      throw new IllegalArgumentException("Id inválido: " + id);
   }

}
