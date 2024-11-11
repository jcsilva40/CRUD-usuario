package com.stfn2.ggas.domain.dtos.basic;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.ClienteContato;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteContatoBasicDTO extends BaseDTO {
   private Long id;
   private String nome;
   private String email;

   public ClienteContatoBasicDTO(ClienteContato entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}