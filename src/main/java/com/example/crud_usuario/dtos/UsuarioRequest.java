package com.example.crud_usuario.dtos;

import com.example.crud_usuario.models.Usuario;

import java.time.LocalDate;

public record UsuarioRequest(
         String email,
         String senha,
         String nome,
         LocalDate dataNascimento
) {
    public UsuarioRequest(Usuario usuario) {
        this(   usuario.getEmail(),
                usuario.getSenha(),
                usuario.getNome(),
                usuario.getDataNascimento()
        );
    }
}
