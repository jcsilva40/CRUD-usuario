package com.application.crud_usuario.dtos;

import com.application.crud_usuario.models.Usuario;
import lombok.Builder;

import java.time.LocalDate;
@Builder
public record UsuarioResponse(
        Integer id,
         String email,
         String nome,
         LocalDate dataNascimento
) {
}
