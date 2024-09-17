package com.example.crud_usuario.dtos;

import java.time.LocalDate;

public record UsuarioUpdateRequest(
        String email,
        String senha,
        String nome,
        LocalDate dataNascimento
) {
}
