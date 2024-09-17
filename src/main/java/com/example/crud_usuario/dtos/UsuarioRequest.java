package com.example.crud_usuario.dtos;

public record UsuarioRequest(
         Integer id,
         String email,
         String senha,
         String nome,
         String dataNascimento
) {
}
