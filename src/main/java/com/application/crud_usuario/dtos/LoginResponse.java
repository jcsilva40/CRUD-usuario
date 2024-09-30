package com.application.crud_usuario.dtos;

import com.application.crud_usuario.models.Usuario;
import lombok.Builder;

@Builder
public record LoginResponse(
         Integer id,
         String email,
         String nome

) {
}
