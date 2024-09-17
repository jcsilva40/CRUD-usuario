package com.example.crud_usuario.dtos;

import com.example.crud_usuario.models.Usuario;

public record LoginResponse(
         Integer id,
         String email,
         String nome

) {
    public LoginResponse(Usuario usuario)
    {
        this(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getNome()
        );

    }
}
