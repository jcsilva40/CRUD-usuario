package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ContatoTipoEnum {
   SECRETARIO(	1L, "Secretária(o)"),
   PORTEIRO	(2L, "Porteiro"),
   ADMINISTRADOR	(3L, "Administrador"),
   SINDICO	(4L, "Sindico"),
   MORADOR	(5L, "Morador"),
   OUTROS	(6L, "Outros"),
   PROPRIETARIO	(7L, "Proprietario");

   private Long id;
   private String descricao;

   private ContatoTipoEnum(Long id, String descricao) {
      this.id = id;
      this.descricao=descricao;
   }

   @JsonValue
   public Long getId() {
      return id;
   }

   public String getDescricao() {
      return descricao;
   }

   public static ContatoTipoEnum toEnum(Long id) {
      if(id==null) {
         return null;
      }

      for (ContatoTipoEnum x : ContatoTipoEnum.values()) {
         if(id.equals(x.getId())) {
            return x;
         }
      }
      throw new IllegalArgumentException("Id inválido: " + id);
   }

}
