package com.stfn2.ggas.config.security.domain.dto;

import com.stfn2.ggas.config.security.domain.Permission;
import com.stfn2.ggas.config.security.domain.User;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserBasicDTO extends BaseDTO {
   private Long id;
   private String userName;
   private String nome;
   private String email;
   private List<Permission> permissions;

   public UserBasicDTO (User entity) {super(); 	}

   @Override
   public Long getId() {
      return this.id;
   }
}