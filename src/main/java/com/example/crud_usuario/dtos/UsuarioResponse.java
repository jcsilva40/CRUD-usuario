package com.example.crud_usuario.dtos;

import com.example.crud_usuario.models.Usuario;

import java.time.LocalDate;

public record UsuarioResponse(
        Integer id,
         String email,
         String nome,
         LocalDate dataNascimento
) {
    public UsuarioResponse(Usuario usuario) {
        this(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getDataNascimento()
        );
    }
}
