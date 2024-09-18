package com.example.crud_usuario.dtos;

import java.time.LocalDate;

public record UsuarioRequest(
         String email,
         String senha,
         String nome,
         LocalDate dataNascimento
) {
}
